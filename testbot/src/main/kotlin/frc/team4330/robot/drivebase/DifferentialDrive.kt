/*----------------------------------------------------------------------------*/
/* Copyright (c) 2008-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.team4330.robot.drivebase

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.IMotorController
import edu.wpi.first.wpilibj.drive.RobotDriveBase
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType
import edu.wpi.first.wpilibj.hal.HAL
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder

/**
 * A class for driving differential drive/skid-steer drive platforms such as the Kit of Parts drive
 * base, "tank drive", or West Coast Drive.
 *
 *
 * These drive bases typically have drop-center / skid-steer with two or more wheels per side
 * (e.g., 6WD or 8WD). This class takes a SpeedController per side. For four and
 * six motor drivetrains, construct and pass in [edu.wpi.first.wpilibj.SpeedControllerGroup]
 * instances as follows.
 *
 *
 * Four motor drivetrain:
 * <pre>`
 * public class Robot {
 * Spark m_frontLeft = new Spark(1);
 * Spark m_rearLeft = new Spark(2);
 * SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
 *
 * Spark m_frontRight = new Spark(3);
 * Spark m_rearRight = new Spark(4);
 * SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
 *
 * DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
 * }
`</pre> *
 *
 *
 * Six motor drivetrain:
 * <pre>`
 * public class Robot {
 * Spark m_frontLeft = new Spark(1);
 * Spark m_midLeft = new Spark(2);
 * Spark m_rearLeft = new Spark(3);
 * SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);
 *
 * Spark m_frontRight = new Spark(4);
 * Spark m_midRight = new Spark(5);
 * Spark m_rearRight = new Spark(6);
 * SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);
 *
 * DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
 * }
`</pre> *
 *
 *
 * A differential drive robot has left and right wheels separated by an arbitrary width.
 *
 *
 * Drive base diagram:
 * <pre>
 * |_______|
 * | |   | |
 * |   |
 * |_|___|_|
 * |       |
</pre> *
 *
 *
 * Each drive() function provides different inverse kinematic relations for a differential drive
 * robot. Motor outputs for the right side are negated, so motor direction inversion by the user is
 * usually unnecessary.
 *
 *
 * This library uses the NED axes convention (North-East-Down as external reference in the world
 * frame): http://www.nuclearprojects.com/ins/images/axis_big.png.
 *
 *
 * The positive X axis points ahead, the positive Y axis points right, and the positive Z axis
 * points down. Rotations follow the right-hand rule, so clockwise rotation around the Z axis is
 * positive.
 *
 *
 * Inputs smaller then {@value edu.wpi.first.wpilibj.drive.RobotDriveBase#kDefaultDeadband} will
 * be set to 0, and larger values will be scaled so that the full range is still used. This
 * deadband value can be changed with [.setDeadband].
 *
 *
 * RobotDrive porting guide:
 * <br></br>[.tankDrive] is equivalent to
 * [edu.wpi.first.wpilibj.RobotDrive.tankDrive] if a deadband of 0 is used.
 * <br></br>[.arcadeDrive] is equivalent to
 * [edu.wpi.first.wpilibj.RobotDrive.arcadeDrive] if a deadband of 0 is used
 * and the the rotation input is inverted eg arcadeDrive(y, -rotation)
 * <br></br>[.curvatureDrive] is similar in concept to
 * [edu.wpi.first.wpilibj.RobotDrive.drive] with the addition of a quick turn
 * mode. However, it is not designed to give exactly the same response.
 */
class DifferentialDrive
/**
 * Construct a DifferentialDrive.
 *
 *
 * To pass multiple motors per side, use a [SpeedControllerGroup]. If a motor needs to be
 * inverted, do so before passing it in.
 */
(private val m_leftMotor: IMotorController, private val m_rightMotor: IMotorController) : RobotDriveBase() {

    private var m_quickStopThreshold = kDefaultQuickStopThreshold
    private var m_quickStopAlpha = kDefaultQuickStopAlpha
    private var m_quickStopAccumulator = 0.0
    private var m_reported = false

    init {
        addChild(m_leftMotor)
        addChild(m_rightMotor)
        instances++
        setName("DifferentialDrive", instances)
    }

    /**
     * Arcade drive method for differential drive platform.
     *
     * @param xSpeed        The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param zRotation     The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
     * positive.
     * @param squaredInputs If set, decreases the input sensitivity at low speeds.
     */
    @JvmOverloads
    fun arcadeDrive(xSpeed: Double, zRotation: Double, squaredInputs: Boolean = true) {
        var xSpeed = xSpeed
        var zRotation = zRotation
        if (!m_reported) {
            HAL.report(tResourceType.kResourceType_RobotDrive, 2, tInstances.kRobotDrive_ArcadeStandard)
            m_reported = true
        }

        xSpeed = limit(xSpeed)
        xSpeed = applyDeadband(xSpeed, m_deadband)

        zRotation = limit(zRotation)
        zRotation = applyDeadband(zRotation, m_deadband)

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squaredInputs) {
            xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed)
            zRotation = Math.copySign(zRotation * zRotation, zRotation)
        }

        val leftMotorOutput: Double
        val rightMotorOutput: Double

        val maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed)

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = maxInput
                rightMotorOutput = xSpeed - zRotation
            } else {
                leftMotorOutput = xSpeed + zRotation
                rightMotorOutput = maxInput
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = xSpeed + zRotation
                rightMotorOutput = maxInput
            } else {
                leftMotorOutput = maxInput
                rightMotorOutput = xSpeed - zRotation
            }
        }

        m_leftMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput)
        m_rightMotor.set(ControlMode.PercentOutput, -limit(rightMotorOutput) * m_maxOutput)

        m_safetyHelper.feed()
    }

    /**
     * Curvature drive method for differential drive platform.
     *
     *
     * The rotation argument controls the curvature of the robot's path rather than its rate of
     * heading change. This makes the robot more controllable at high speeds. Also handles the
     * robot's quick turn functionality - "quick turn" overrides constant-curvature turning for
     * turn-in-place maneuvers.
     *
     * @param xSpeed      The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param zRotation   The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
     * positive.
     * @param isQuickTurn If set, overrides constant-curvature turning for
     * turn-in-place maneuvers.
     */
    fun curvatureDrive(xSpeed: Double, zRotation: Double, isQuickTurn: Boolean) {
        var xSpeed = xSpeed
        var zRotation = zRotation
        if (!m_reported) {
            // HAL.report(tResourceType.kResourceType_RobotDrive, 2, tInstances.kRobotDrive_Curvature);
            m_reported = true
        }

        xSpeed = limit(xSpeed)
        xSpeed = applyDeadband(xSpeed, m_deadband)

        zRotation = limit(zRotation)
        zRotation = applyDeadband(zRotation, m_deadband)

        val angularPower: Double
        val overPower: Boolean

        if (isQuickTurn) {
            if (Math.abs(xSpeed) < m_quickStopThreshold) {
                m_quickStopAccumulator = (1 - m_quickStopAlpha) * m_quickStopAccumulator + m_quickStopAlpha * limit(zRotation) * 2.0
            }
            overPower = true
            angularPower = zRotation
        } else {
            overPower = false
            angularPower = Math.abs(xSpeed) * zRotation - m_quickStopAccumulator

            if (m_quickStopAccumulator > 1) {
                m_quickStopAccumulator -= 1.0
            } else if (m_quickStopAccumulator < -1) {
                m_quickStopAccumulator += 1.0
            } else {
                m_quickStopAccumulator = 0.0
            }
        }

        var leftMotorOutput = xSpeed + angularPower
        var rightMotorOutput = xSpeed - angularPower

        // If rotation is overpowered, reduce both outputs to within acceptable range
        if (overPower) {
            if (leftMotorOutput > 1.0) {
                rightMotorOutput -= leftMotorOutput - 1.0
                leftMotorOutput = 1.0
            } else if (rightMotorOutput > 1.0) {
                leftMotorOutput -= rightMotorOutput - 1.0
                rightMotorOutput = 1.0
            } else if (leftMotorOutput < -1.0) {
                rightMotorOutput -= leftMotorOutput + 1.0
                leftMotorOutput = -1.0
            } else if (rightMotorOutput < -1.0) {
                leftMotorOutput -= rightMotorOutput + 1.0
                rightMotorOutput = -1.0
            }
        }

        m_leftMotor.set(ControlMode.PercentOutput, leftMotorOutput * m_maxOutput)
        m_rightMotor.set(ControlMode.PercentOutput, -rightMotorOutput * m_maxOutput)

        m_safetyHelper.feed()
    }

    /**
     * Tank drive method for differential drive platform.
     *
     * @param leftSpeed     The robot left side's speed along the X axis [-1.0..1.0]. Forward is
     * positive.
     * @param rightSpeed    The robot right side's speed along the X axis [-1.0..1.0]. Forward is
     * positive.
     * @param squaredInputs If set, decreases the input sensitivity at low speeds.
     */
    @JvmOverloads
    fun tankDrive(leftSpeed: Double, rightSpeed: Double, squaredInputs: Boolean = true) {
        var leftSpeed = leftSpeed
        var rightSpeed = rightSpeed
        if (!m_reported) {
            HAL.report(tResourceType.kResourceType_RobotDrive, 2, tInstances.kRobotDrive_Tank)
            m_reported = true
        }

        leftSpeed = limit(leftSpeed)
        leftSpeed = applyDeadband(leftSpeed, m_deadband)

        rightSpeed = limit(rightSpeed)
        rightSpeed = applyDeadband(rightSpeed, m_deadband)

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squaredInputs) {
            leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed)
            rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed)
        }

        m_leftMotor.set(ControlMode.PercentOutput, leftSpeed * m_maxOutput)
        m_rightMotor.set(ControlMode.PercentOutput, -rightSpeed * m_maxOutput)

        m_safetyHelper.feed()
    }

    /**
     * Sets the QuickStop speed threshold in curvature drive.
     *
     *
     * QuickStop compensates for the robot's moment of inertia when stopping after a QuickTurn.
     *
     *
     * While QuickTurn is enabled, the QuickStop accumulator takes on the rotation rate value
     * outputted by the low-pass filter when the robot's speed along the X axis is below the
     * threshold. When QuickTurn is disabled, the accumulator's value is applied against the computed
     * angular power request to slow the robot's rotation.
     *
     * @param threshold X speed below which quick stop accumulator will receive rotation rate values
     * [0..1.0].
     */
    fun setQuickStopThreshold(threshold: Double) {
        m_quickStopThreshold = threshold
    }

    /**
     * Sets the low-pass filter gain for QuickStop in curvature drive.
     *
     *
     * The low-pass filter filters incoming rotation rate commands to smooth out high frequency
     * changes.
     *
     * @param alpha Low-pass filter gain [0.0..2.0]. Smaller values result in slower output changes.
     * Values between 1.0 and 2.0 result in output oscillation. Values below 0.0 and
     * above 2.0 are unstable.
     */
    fun setQuickStopAlpha(alpha: Double) {
        m_quickStopAlpha = alpha
    }

    override fun stopMotor() {
        m_leftMotor.set(ControlMode.PercentOutput, 0.0)
        m_rightMotor.set(ControlMode.PercentOutput, 0.0)
        m_safetyHelper.feed()
    }

    override fun getDescription(): String {
        return "DifferentialDrive"
    }

    override fun initSendable(builder: SendableBuilder) {
        builder.setSmartDashboardType("DifferentialDrive")
        builder.addDoubleProperty("Left Motor Speed", { m_leftMotor.motorOutputPercent }, { x -> m_leftMotor.set(ControlMode.PercentOutput, x) })
        builder.addDoubleProperty(
                "Right Motor Speed",
                { -m_rightMotor.motorOutputPercent }
        ) { x -> m_rightMotor.set(ControlMode.PercentOutput, -x) }
    }

    companion object {
        val kDefaultQuickStopThreshold = 0.2
        val kDefaultQuickStopAlpha = 0.1

        private var instances = 0
    }
}
/**
 * Arcade drive method for differential drive platform.
 * The calculated values will be squared to decrease sensitivity at low speeds.
 *
 * @param xSpeed    The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
 * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
 * positive.
 */
/**
 * Tank drive method for differential drive platform.
 * The calculated values will be squared to decrease sensitivity at low speeds.
 *
 * @param leftSpeed  The robot's left side speed along the X axis [-1.0..1.0]. Forward is
 * positive.
 * @param rightSpeed The robot's right side speed along the X axis [-1.0..1.0]. Forward is
 * positive.
 */
