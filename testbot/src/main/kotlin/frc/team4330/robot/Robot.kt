package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.Climber
import frc.team4330.robot.subsystems.Mouth
import frc.team4330.robot.subsystems.robotDrive

class Robot : IterativeRobot() {

    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)

        var tank: robotDrive = robotDrive()

        val manager: DashboardManager = DashboardManager()

        val mouth: Mouth = Mouth()

        val mRobot: Scheduler = Scheduler.getInstance()

        val climb: Climber = Climber()

//        val motion: motion = motion()
    }

//    private lateinit var scheduler: Scheduler

    override fun robotInit() {
        CameraServer.getInstance().startAutomaticCapture()

        RobotMap.RIGHT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.LEFT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.RIGHT_TALON.setSensorPhase(true)

    }

    override fun disabledInit() {
        RobotMap.COMP.stop()
    }

    override fun autonomousInit() {
//        mRobot.removeAll()
//        val test: DeliverCube = DeliverCube()
//        test.start()
//        mRobot.enable()

    }

    override fun teleopInit() {

    }

    override fun testInit() {
    }


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {
        RobotMap.COMP.start()
    }

    override fun teleopPeriodic() {
        manager.start()
        RobotMap.COMP.start()
        tank.curveDrive(xbox)


        //   if (xbox.bButton) RobotMap.JAW.set(true)
        //   if (xbox.aButton) RobotMap.JAW.set(false)
        climb.move(xbox)
        if (xbox.aButton) RobotMap.JAW.set(true)
        else if (xbox.bButton) RobotMap.JAW.set(false)
    }

    override fun testPeriodic() {
//        motion.move()
    }
}