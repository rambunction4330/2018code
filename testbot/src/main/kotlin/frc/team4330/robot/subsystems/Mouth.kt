package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.RobotMap


class Mouth : SubsystemBase() {

    private var mouthUp: Boolean
//    private var mouthOpen: Boolean

    init {
        mouthUp = false
        //       mouthOpen = false
    }

    //    fun moveMouthUp(xbox: Input) {            //moves mouth Up and down with x button
//        if (!mouthUp && xbox.xButtonPressed) {
//            RobotMap.JAW.set(true)
//            mouthUp = true
//        }
//        else {
//            RobotMap.JAW.set(false)
//            mouthUp = false
//        }
//    }
    fun moveMouthUp() {            //moves mouth Up and down
        //    if (!mouthUp) {
        RobotMap.JAW.set(true)
        mouthUp = true
        //        mouthUp = true
        //    }
        //    else {
        //        RobotMap.JAW.set(false)
        //        mouthUp = false
        //    }
    }

    fun moveMouthDown() {
        RobotMap.JAW.set(false)
        mouthUp = false
    }

//    fun moveMouth() { //toggle
//        mouthUp != mouthUp
//        RobotMap.JAW.set(mouthUp)
//    }


    fun succ() {                            //succs in cube
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, -0.3)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, 0.3)
    }

    fun spit() {                            //spits out cube
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, 0.3)
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, -0.3)
    }

    fun stopLips() {
        RobotMap.LIP_LEFT.set(ControlMode.PercentOutput, 0.0 )
        RobotMap.LIP_RIGHT.set(ControlMode.PercentOutput, 0.0)
    }

    fun openWide() {                   //opens "TEETH"
        RobotMap.TEETH.set(false)
    }

    fun closeMouth() {
        RobotMap.TEETH.set(true)
    }

//    fun openClose() {//toggle
//        mouthOpen != mouthOpen
//        RobotMap.TEETH.set(mouthOpen)
//    }
}