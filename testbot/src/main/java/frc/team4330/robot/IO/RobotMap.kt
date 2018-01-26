package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX


object RobotMap {
    val RIGHT_VICTOR_1: WPI_VictorSPX = WPI_VictorSPX(1)
    val RIGHT_VICTOR_2: WPI_VictorSPX = WPI_VictorSPX(4)
    val LEFT_VICTOR_1: WPI_VictorSPX = WPI_VictorSPX(2)
    val LEFT_VICTOR_2: WPI_VictorSPX = WPI_VictorSPX(3)
    val LEFT_TALON = 2
    val RIGHT_TALON = 3

    val DRIVE_JOYSTICK = 0

    val PCM_CAN = 0

    val PISTON_ONE_0 = 0
    val PISTON_ONE_1 = 1

    val PISTON_TWO_0 = 2
    val PISTON_TWO_1 = 3

}