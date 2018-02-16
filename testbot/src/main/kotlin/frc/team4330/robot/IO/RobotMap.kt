package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.NidecBrushless
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.Solenoid


object RobotMap {
    // Drive motors
    val RIGHT_VICTOR: WPI_VictorSPX = WPI_VictorSPX(4)
    val RIGHT_VICTOR2: WPI_VictorSPX = WPI_VictorSPX(9)
    val LEFT_VICTOR: WPI_VictorSPX = WPI_VictorSPX(2)
    val LEFT_VICTOR2: WPI_VictorSPX = WPI_VictorSPX(8)
    val LEFT_TALON: WPI_TalonSRX = WPI_TalonSRX(6)
    val RIGHT_TALON: WPI_TalonSRX = WPI_TalonSRX(5)

    // Climbing
    val CLIMB_LEFT: TalonSRX = TalonSRX(12)
    val SPOOL1: TalonSRX = TalonSRX(7)

    // Mouth
    val LIP_LEFT: VictorSPX = VictorSPX(11)
    val LIP_RIGHT: VictorSPX = VictorSPX(10)

    // Controllers
    val DRIVE_JOYSTICK = 0

    // Pneumatics
    val COMP: Compressor = Compressor(0)
    val rightShift: Solenoid = Solenoid(0)
    val leftShift: Solenoid = Solenoid(2)
    val JAW: Solenoid = Solenoid(3)
    val TEETH: Solenoid = Solenoid(1)

    // Sensors
    val rightEncPos = RIGHT_TALON.getSelectedSensorPosition(0)
    val leftEncPos = LEFT_TALON.getSelectedSensorPosition(0)
    val rightEncVel = RIGHT_TALON.getSelectedSensorVelocity(0)
    val leftEncVel = LEFT_TALON.getSelectedSensorVelocity(0)

    val gyro: AHRS = AHRS(SPI.Port.kMXP)

    //Nidec Robot
    val nidecMotor: NidecBrushless = NidecBrushless(1,5)

}