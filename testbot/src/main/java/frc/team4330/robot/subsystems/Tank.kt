package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.drivebase.Talons
import frc.team4330.robot.drivebase.Victor

class Tank : SubsystemBase() {

    val leftTal: Talons = Talons(RobotMap.LEFT_TALON)
    val rightTal: Talons = Talons(RobotMap.RIGHT_TALON)
    val leftVic: Victor = Victor(RobotMap.LEFT_VICTOR_PWM)
    val rightVic: Victor = Victor(RobotMap.RIGHT_VICTOR_PWM)

//    val rightE = Encoders(RobotMap.RIGHT_ENCODER1, RobotMap.RIGHT_ENCODER2)
//    val leftE = Encoders(RobotMap.LEFT_ENCODER1, RobotMap.LEFT_ENCODER2)


    fun drive(leftY: Double, rightY: Double) {
        leftTal.speed = leftY
        leftVic.speed = leftY

        rightTal.speed = rightY * -1
        rightVic.speed = rightY * -1


    }

}