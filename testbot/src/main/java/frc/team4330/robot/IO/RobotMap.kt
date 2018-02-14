package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.VictorSP


object RobotMap {
    val RIGHT_VICTOR_1: VictorSP = VictorSP(0)
    val RIGHT_VICTOR_2: VictorSP = VictorSP(1)
    val LEFT_VICTOR_1: VictorSP = VictorSP( 2)
    val LEFT_VICTOR_2: VictorSP = VictorSP( 3)

    val DRIVE_JOYSTICK = 0

    val PISTON_ONE_0 = 0
    val PISTON_ONE_1 = 1

    val PISTON_TWO_0 = 2
    val PISTON_TWO_1 = 3

}