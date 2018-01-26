package frc.team4330.robot

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.robotDrive

class Robot : IterativeRobot() {

    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)


//        val comp: Compressor = Compressor(0)
//        val pist1: SingleSolenoid = SingleSolenoid(0)
//        val pist2: SingleSolenoid = SingleSolenoid(1)

        val mDrive: robotDrive = robotDrive()
    }

    private lateinit var scheduler: Scheduler

    override fun robotInit() {
    }

    override fun disabledInit() {
//        comp.stop()
    }

    override fun autonomousInit() {
//        comp.start()
    }

    override fun teleopInit() {
//        comp.start()
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