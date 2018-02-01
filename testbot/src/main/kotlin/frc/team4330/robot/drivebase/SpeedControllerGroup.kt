/*----------------------------------------------------------------------------*/
/* Copyright (c) 2016-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.drivebase


import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.IMotorController
import edu.wpi.first.wpilibj.SendableBase
import edu.wpi.first.wpilibj.SpeedController
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder

/**
 * Allows multiple [SpeedController] objects to be linked together.
 */
class SpeedControllerGroup
/**
 * Create a new SpeedControllerGroup with the provided SpeedControllers.
 *
 * @param speedControllers The SpeedControllers to add
 */
(speedController: IMotorController,
 vararg speedControllers: IMotorController) : SendableBase(), SpeedController {
    private var m_isInverted = false
    private val m_speedControllers: Array<IMotorController?>

    init {
        m_speedControllers = arrayOfNulls<IMotorController>(speedControllers.size + 1)
        m_speedControllers[0] = speedController
        addChild(speedController)
        for (i in speedControllers.indices) {
            m_speedControllers[i + 1] = speedControllers[i]
            addChild(speedControllers[i])
        }
        instances++
        setName("SpeedControllerGroup", instances)
    }

    override fun set(speed: Double) {
        for (speedController in m_speedControllers) {
            speedController?.set(ControlMode.PercentOutput, if (m_isInverted) -speed else speed)
        }
    }

    override fun get(): Double {
        return if (m_speedControllers.size > 0) {
            m_speedControllers[0]?.motorOutputPercent!! * if (m_isInverted) -1 else 1
        } else 0.0
    }

    override fun setInverted(isInverted: Boolean) {
        m_isInverted = isInverted
    }

    override fun getInverted(): Boolean {
        return m_isInverted
    }

    override fun disable() {
        for (speedController in m_speedControllers) {
            speedController?.set(ControlMode.PercentOutput, 0.0)
        }
    }

    override fun stopMotor() {
        for (speedController in m_speedControllers) {
            speedController?.set(ControlMode.PercentOutput, 0.0)
        }
    }

    override fun pidWrite(output: Double) {
        set(output)
    }

    override fun initSendable(builder: SendableBuilder) {
        builder.setSmartDashboardType("Speed Controller")
        builder.setSafeState { this.stopMotor() }
        builder.addDoubleProperty("Value", this::get, this::set)
    }

    companion object {
        private var instances = 0
    }
}

