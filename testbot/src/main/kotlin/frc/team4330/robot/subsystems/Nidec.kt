package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.RobotMap


class Nidec {

    init {

    }

    fun turnUp() {
        RobotMap.nidecMotor.set(0.4)
    }

    fun turnDown() {
        RobotMap.nidecMotor.set(-0.02)
    }

    fun stop() {
        RobotMap.nidecMotor.stopMotor()
    }

}