package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class robotDrive : SubsystemBase() {

    private val rightTal: WPI_TalonSRX
    private val leftTal: WPI_TalonSRX
    private val rightVic: WPI_VictorSPX
    private val leftVic: WPI_VictorSPX
    private var reverse: Boolean = false
    private val mDrive: DifferentialDrive
    private val mRight: SpeedControllerGroup
    private val mLeft: SpeedControllerGroup

    init {
        rightVic = WPI_VictorSPX(RobotMap.RIGHT_VICTOR_PWM)
        rightTal = WPI_TalonSRX(RobotMap.RIGHT_TALON)

        leftVic = WPI_VictorSPX(RobotMap.LEFT_VICTOR_PWM)
        leftTal = WPI_TalonSRX(RobotMap.LEFT_TALON)


//        rightVic.follow(rightTal)
//        leftVic.follow(leftTal)

        mLeft = SpeedControllerGroup(rightTal, rightVic)
        mRight = SpeedControllerGroup(leftTal, leftVic)

        mDrive = DifferentialDrive(mLeft, mRight)
    }

    fun curveDrive(xbox: Input) {
        mDrive.curvatureDrive(xbox.joystickLeftYAxis, xbox.joystickRightXAxis, xbox.isRightTriggerPressed())
    }

}