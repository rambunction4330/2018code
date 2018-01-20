package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.VictorSP


object RobotMap {
    val RIGHT_VICTOR: WPI_VictorSPX = WPI_VictorSPX(5)
    val LEFT_VICTOR: WPI_VictorSPX = WPI_VictorSPX(4)
    val LEFT_TALON: WPI_TalonSRX = WPI_TalonSRX(2)
    val RIGHT_TALON: WPI_TalonSRX = WPI_TalonSRX(3)

    val CLIMB_RIGHT: VictorSP = VictorSP(8)
    val CLIMB_LEFT: VictorSP = VictorSP(9)

    val SPOOL1: VictorSP = VictorSP(0)
    val SPOOL2: VictorSP = VictorSP(1)

    val DRIVE_JOYSTICK = 0

    val RIGHT_ENCODER1 = 8
    val RIGHT_ENCODER2 = 9
    val LEFT_ENCODER1 = 6
    val LEFT_ENCODER2 = 7

    val PCM_CAN = 0

    val PISTON_ONE_0 = 0
    val PISTON_ONE_1 = 1

    val PISTON_TWO_0 = 2
    val PISTON_TWO_1 = 3

}