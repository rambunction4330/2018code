package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.SpeedController
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.VictorSP
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class robotDrive {
    private val rightVic1: SpeedController
    private val leftVic1: SpeedController
    private val rightVic2: SpeedController
    private val leftVic2: SpeedController
    private val mDriveR: SpeedControllerGroup
    private val mDriveL: SpeedControllerGroup
    private val mDrive: DifferentialDrive
    private var reverse: Boolean = false

    init {
        rightVic1 = VictorSP(RobotMap.RIGHT_TALON)
        rightVic2 = VictorSP(RobotMap.RIGHT_VICTOR_PWM)

        leftVic1 = VictorSP(RobotMap.LEFT_TALON)
        leftVic2 = VictorSP(RobotMap.LEFT_VICTOR_PWM)

        mDriveR = SpeedControllerGroup(rightVic1, rightVic2)
        mDriveL = SpeedControllerGroup(leftVic1, leftVic2)

        mDrive = DifferentialDrive(mDriveL, mDriveR)

    }

    fun autoDrive(xbox: Input) {
        mDrive.curvatureDrive(xbox.joystickLeftYAxis, xbox.joystickRightXAxis, xbox.ifTriggerPressed())
    }


}