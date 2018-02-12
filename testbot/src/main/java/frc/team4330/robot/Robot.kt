package frc.team4330.robot

import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.Commands.TestCommand
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.robotDrive


class Robot : IterativeRobot() {


    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)

        val mDrive: robotDrive = robotDrive()

        val mRobot: Scheduler = Scheduler.getInstance()

    }


    override fun robotInit() {
        CameraServer.getInstance().startAutomaticCapture()

    }

    override fun disabledInit() {
    }

    override fun autonomousInit() {

        mRobot.removeAll()
        val drivecommand: TestCommand =  TestCommand()
        drivecommand.start()
        mRobot.enable()

    }

    override fun teleopInit() {

    }

    override fun testInit() {

    }


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {
        mRobot.run()
    }

    override fun teleopPeriodic() {
        mDrive.tankDrive(xbox)
    }

    override fun testPeriodic() {}
}