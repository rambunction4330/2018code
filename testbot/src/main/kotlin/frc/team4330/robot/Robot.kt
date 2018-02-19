package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.CommandGroups.TestMouth
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Pathfinder.motion
import frc.team4330.robot.subsystems.Climber
import frc.team4330.robot.subsystems.Mouth
import frc.team4330.robot.subsystems.robotDrive

class Robot : TimedRobot() {

    companion object {
        val xbox: Input = RobotMap.DRIVE_JOYSTICK

        var tank: robotDrive = robotDrive()

        val manager: DashboardManager = DashboardManager()

        val mouth: Mouth = Mouth()

        val mRobot: Scheduler = Scheduler.getInstance()

        val climb: Climber = Climber()

        val motion: motion = motion()

        val test: TestMouth = TestMouth()
    }

//    private lateinit var scheduler: Scheduler

    override fun robotInit() {

        val inst: NetworkTableInstance = NetworkTableInstance.create()
        val table: NetworkTable = inst.getTable("datatable")
        CameraServer.getInstance().startAutomaticCapture()

        RobotMap.RIGHT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.LEFT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.RIGHT_TALON.setSensorPhase(true)
        RobotMap.RIGHT_TALON.configOpenloopRamp(.2, 10)
        RobotMap.LEFT_TALON.configOpenloopRamp(.2, 10)
    }

    override fun disabledInit() {
        RobotMap.COMP.stop()
    }

    override fun autonomousInit() {
        mRobot.removeAll()
        test.start()
        mRobot.enable()
    }

    override fun teleopInit() {

    }

    override fun testInit() {
    }


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {
        RobotMap.COMP.start()
        mRobot.run()
    }

    override fun teleopPeriodic() {
        manager.start()
        RobotMap.COMP.start()
        tank.curveDrive(xbox)

        RobotMap.nidecMotor.enable()
        //   if (xbox.bButton) RobotMap.JAW.set(true)
        //   if (xbox.aButton) RobotMap.JAW.set(false)
        climb.move(xbox)
//        if (xbox.aButton) RobotMap.nidecMotor.set(0.001)
//        else if (xbox.bButton) RobotMap.nidecMotor.stopMotor()//RobotMap.nidecMotor.set(0.000001)

        var group: CommandGroup = CommandGroup()
        when {
            xbox.bButton -> mouth.closeMouth()//group.addSequential(TestLipSpinButton())
            xbox.aButton -> mouth.openWide()
        }
        mRobot.add(group)
        mRobot.run()


    }

    override fun testPeriodic() {
//        motion.move()
//        print("test")
        //    motion.test()
    }
}