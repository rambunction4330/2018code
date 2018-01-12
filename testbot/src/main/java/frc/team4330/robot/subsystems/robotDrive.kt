package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint


class robotDrive : SubsystemBase() {

    private val rightTal: WPI_TalonSRX
    private val leftTal: WPI_TalonSRX
    private val rightVic: WPI_VictorSPX
    private val leftVic: WPI_VictorSPX
    private var reverse: Boolean = false
    private val mLeft: SpeedControllerGroup
    private val mRight: SpeedControllerGroup
    private val mDrive: DifferentialDrive

    init {
        rightVic = WPI_VictorSPX(RobotMap.RIGHT_VICTOR_1)
        rightTal = WPI_TalonSRX(RobotMap.RIGHT_TALON)

        leftVic = WPI_VictorSPX(RobotMap.LEFT_VICTOR_1)
        leftTal = WPI_TalonSRX(RobotMap.LEFT_TALON)

        mLeft = SpeedControllerGroup(leftTal, leftVic)
        mRight = SpeedControllerGroup(rightTal, rightVic)

        mDrive = DifferentialDrive(mLeft, mRight)

    }

    fun autoDrive(xbox: Input) {
       mDrive.curvatureDrive(xbox.joystickLeftYAxis, xbox.joystickLeftXAxis, xbox.joystickLeftPress)
    }

    fun pathgen() {

    }
}