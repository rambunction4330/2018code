package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class robotDrive {

    val mDrive: DifferentialDrive
    val mRight: SpeedControllerGroup
    val mLeft: SpeedControllerGroup

    init {

        mLeft = SpeedControllerGroup(RobotMap.LEFT_VICTOR_1, RobotMap.LEFT_VICTOR_2)
        mRight = SpeedControllerGroup(RobotMap.RIGHT_VICTOR_1, RobotMap.RIGHT_VICTOR_2)
        mDrive = DifferentialDrive(mLeft, mRight)
    }

    fun tankDrive(xbox: Input) {
        mDrive.arcadeDrive(xbox.joystickLeftYAxis*-1.0, xbox.joystickRightXAxis *0.8, xbox.isTriggerPressed())
    }

    fun autoDrive(speed: Double, rotation: Double, quickturn: Boolean) {
        mDrive.curvatureDrive(speed, rotation, quickturn)
    }

    fun stop() {
        mDrive.curvatureDrive(0.0,0.0,false)
    }
}