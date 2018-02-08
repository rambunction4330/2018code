package frc.team4330.robot

import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.IterativeRobot
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.robotDrive

class Robot : IterativeRobot() {

    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)

        val mDrive: robotDrive = robotDrive()
    }


    override fun robotInit() {
        CameraServer.getInstance().startAutomaticCapture()
    }

    override fun disabledInit() {
    }

    override fun autonomousInit() {
    }

    override fun teleopInit() {
    }

    override fun testInit() {
    }


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {}

    override fun teleopPeriodic() {
        mDrive.tankDrive(xbox)
    }

    override fun testPeriodic() {}
}