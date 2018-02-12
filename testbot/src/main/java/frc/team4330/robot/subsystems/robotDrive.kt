package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class robotDrive {

    val mDrive: DifferentialDrive

    init {

        RobotMap.RIGHT_VICTOR_1.configOpenloopRamp(.2, 0)
        RobotMap.LEFT_VICTOR_1.configOpenloopRamp(.2, 0)
        RobotMap.RIGHT_VICTOR_2.follow(RobotMap.RIGHT_VICTOR_1)
        RobotMap.LEFT_VICTOR_2.follow(RobotMap.LEFT_VICTOR_1)

        mDrive = DifferentialDrive(RobotMap.LEFT_VICTOR_1, RobotMap.RIGHT_VICTOR_1)
    }

    fun tankDrive(xbox: Input) {
        mDrive.arcadeDrive(xbox.joystickLeftYAxis * -1.0, xbox.joystickRightXAxis * 0.7, xbox.isTriggerPressed())
    }

    fun autoDrive(speed: Double, rotation: Double, quickturn: Boolean) {
        mDrive.curvatureDrive(speed, rotation, quickturn)
    }

    fun stop() {
        mDrive.curvatureDrive(0.0,0.0,false)
    }
}