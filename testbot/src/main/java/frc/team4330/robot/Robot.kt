package frc.team4330.robot

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.IO.RobotMap

class Robot : IterativeRobot() {
    override fun robotInit() {
        CameraServer.getInstance().startAutomaticCapture()

    }

    override fun disabledInit() {}

    override fun autonomousInit() {}

    override fun teleopInit() {}

    override fun testInit() {}


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {}

    override fun teleopPeriodic() {}

    override fun testPeriodic() {}
}