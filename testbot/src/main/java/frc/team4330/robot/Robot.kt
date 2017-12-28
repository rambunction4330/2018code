package frc.team4330.robot

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.Tank

class Robot : IterativeRobot() {

    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)

    }

    private lateinit var scheduler: Scheduler

    override fun robotInit() {}

    override fun disabledInit() {}

    override fun autonomousInit() {}

    override fun teleopInit() {}

    override fun testInit() {}


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {}

    override fun teleopPeriodic() {
        Tank().drive(xbox.joystickLeftYAxis, xbox.joystickRightYAxis)
    }

    override fun testPeriodic() {}
}