package frc.team4330.robot.subsystems

//import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.drivebase.DifferentialDrive

class robotDrive : SubsystemBase() {

    private var reverse: Boolean = false
    private val mDrive: DifferentialDrive
//    private val right: EncoderFollower
//    private val left: EncoderFollower
    var shifted: Boolean

    init {
//        rightVic.follow(rightTal)
//        leftVic.follow(leftTal)
        RobotMap.RIGHT_VICTOR.follow(RobotMap.RIGHT_TALON)
        RobotMap.LEFT_TALON.follow(RobotMap.LEFT_TALON)

        mDrive = DifferentialDrive(RobotMap.LEFT_TALON, RobotMap.RIGHT_TALON)

//        right = EncoderFollower()
//        left = EncoderFollower()
        shifted = false
    }

    fun curveDrive(xbox: Input) {
        mDrive.curvatureDrive(.8 * xbox.joystickLeftYAxis, xbox.joystickRightXAxis, xbox.isRightTriggerPressed())
    }

    fun upShift() {
        if (!shifted) RobotMap.rightShift.forward(); shifted = true
    }

    fun downShift() {
        if (shifted) RobotMap.rightShift.reverse(); shifted = false
    }
}