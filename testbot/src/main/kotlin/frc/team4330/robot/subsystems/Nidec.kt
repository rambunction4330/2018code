package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.RobotMap


class Nidec {

    init {

    }

    fun turnUp() {
        RobotMap.nidecMotor.set(0.4)
    }

    fun stop() {
        RobotMap.nidecMotor.stopMotor()
    }

}