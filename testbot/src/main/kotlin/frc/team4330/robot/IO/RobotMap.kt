package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.Solenoid
import edu.wpi.first.wpilibj.XboxController


object RobotMap {
    // Drive motors
    val RIGHT_VICTOR: WPI_VictorSPX
    val RIGHT_VICTOR2: WPI_VictorSPX
    val LEFT_VICTOR: WPI_VictorSPX
    val LEFT_VICTOR2: WPI_VictorSPX
    val LEFT_TALON: WPI_TalonSRX
    val RIGHT_TALON: WPI_TalonSRX
    // Climbing
    val CLIMB_LEFT: TalonSRX
    val SPOOL1: TalonSRX
    // Mouth
    val LIP_LEFT: VictorSPX
    val LIP_RIGHT: VictorSPX

    init {
        RIGHT_VICTOR = WPI_VictorSPX(4)
        RIGHT_VICTOR2 = WPI_VictorSPX(9)
        LEFT_VICTOR = WPI_VictorSPX(2)
        LEFT_VICTOR2 = WPI_VictorSPX(8)
        LEFT_TALON = WPI_TalonSRX(6)
        RIGHT_TALON = WPI_TalonSRX(5)

        // Climbing
        CLIMB_LEFT = TalonSRX(12)
        SPOOL1 = TalonSRX(7)

        // Mouth
        LIP_LEFT = VictorSPX(11)
        LIP_RIGHT = VictorSPX(10)
    }
    // Controllers
    //   val DRIVE_JOYSTICK = XboxController(0)
    val XBOX_INTAKE = XboxController(1)
    val XBOX_OI = XboxController(0)


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
//    val nidecMotor: NidecBrushless = NidecBrushless(1, 0)


}