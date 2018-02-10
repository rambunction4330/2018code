package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.NidecBrushless
import frc.team4330.robot.subsystems.SingleSolenoid


object RobotMap {
    // Drive motors
    val RIGHT_VICTOR: WPI_VictorSPX = WPI_VictorSPX(5)
    val LEFT_VICTOR: WPI_VictorSPX = WPI_VictorSPX(4)
    val LEFT_TALON: WPI_TalonSRX = WPI_TalonSRX(2)
    val RIGHT_TALON: WPI_TalonSRX = WPI_TalonSRX(3)

    // Climbing
    val CLIMB_LEFT: TalonSRX = TalonSRX(8)
    val SPOOL1: TalonSRX = TalonSRX(6)
    val SPOOL2: VictorSPX = VictorSPX(7)

    // Mouth
    val LIP_LEFT: TalonSRX = TalonSRX(9)
    val LIP_RIGHT: VictorSPX = VictorSPX(10)

    // Controllers
    val DRIVE_JOYSTICK = 0

    // Pneumatics
    val PCM_CAN = 1
    val rightShift: SingleSolenoid = SingleSolenoid(1)
    val leftShift: SingleSolenoid = SingleSolenoid(2)
    val JAW: SingleSolenoid = SingleSolenoid(3)
    val TEETH: SingleSolenoid = SingleSolenoid(4)

    // Sensors
    val rightEncPos = RIGHT_TALON.getSelectedSensorPosition(0)
    val leftEncPos = LEFT_TALON.getSelectedSensorPosition(0)
    val rightEncVel = RIGHT_TALON.getSelectedSensorVelocity(0)
    val leftEncVel = LEFT_TALON.getSelectedSensorVelocity(0)

    val gyro: AHRS = AHRS(I2C.Port.kMXP)

    //Nidec Robot
    val nidecMotor: NidecBrushless = NidecBrushless(1,5)

}