package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap


class Mouth : SubsystemBase() {

    private var mouthUp: Boolean

    init {
        mouthUp = false
    }
    fun moveMouthUp(xbox: Input) {            //moves mouth Up and down with x button
        if (!mouthUp && xbox.xButtonPressed) {
            RobotMap.JAW.forward()
            mouthUp = true
        }
        else {
            RobotMap.JAW.reverse()
            mouthUp = false
        }
    }

    fun succ() {                            //succs in cube
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, 0.6)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, 0.6)
    }

    fun spit() {                            //spits out cube
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, -0.6)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput,-0.6 )
    }

    fun stopLips() {
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, 0.0 )
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput,0.0 )
    }

    fun openWide() {                   //opens "TEETH"
        RobotMap.TEETH.forward()
    }

    fun closeMouth() {
        RobotMap.TEETH.reverse()
    }
}