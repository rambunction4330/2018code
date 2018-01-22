package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class robotDrive : SubsystemBase() {

    private var reverse: Boolean = false
    private val mDrive: DifferentialDrive
    private val mRight: SpeedControllerGroup
    private val mLeft: SpeedControllerGroup
//    private val right: EncoderFollower
//    private val left: EncoderFollower

    init {
//        rightVic.follow(rightTal)
//        leftVic.follow(leftTal)

        mLeft = SpeedControllerGroup(RobotMap.LEFT_TALON, RobotMap.LEFT_VICTOR)
        mRight = SpeedControllerGroup(RobotMap.RIGHT_TALON, RobotMap.RIGHT_VICTOR)

        mDrive = DifferentialDrive(mLeft, mRight)

//        right = EncoderFollower()
//        left = EncoderFollower()
    }

    fun curveDrive(xbox: Input) {
        mDrive.curvatureDrive(.8 * xbox.joystickLeftYAxis, xbox.joystickRightXAxis, xbox.isRightTriggerPressed())
    }

   
}