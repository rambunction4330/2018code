package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.RobotMap

class Mouth : SubsystemBase() {

    fun moveMouthUp() {            //moves mouth Up and down
        RobotMap.JAW.set(true)
    }

    fun moveMouthDown() {
        RobotMap.JAW.set(false)
    }

    fun succ() {                            //succs in cube
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, -Constants.LIP_SPEED)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, Constants.LIP_SPEED)
    }

    fun spit() {                            //spits out cube
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, Constants.LIP_SPEED)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, -Constants.LIP_SPEED)
    }

    fun stopLips() {
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, 0.0)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, 0.0)
    }

    fun openWide() {            //opens "TEETH"
        RobotMap.TEETH.set(false)
    }

    fun closeMouth() {
        RobotMap.TEETH.set(true)
    }
}