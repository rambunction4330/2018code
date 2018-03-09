package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.CommandGroups.DeliverCubeAuto
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Pathfinder.motionCommand
import frc.team4330.robot.subsystems.AutonomousManager
import frc.team4330.robot.subsystems.Climber
import frc.team4330.robot.subsystems.Mouth
import frc.team4330.robot.subsystems.robotDrive

class Robot : TimedRobot() {

    companion object {
        //       val xbox: XboxController = RobotMap.DRIVE_JOYSTICK
        val xbox2: XboxController = RobotMap.XBOX_INTAKE
        val xboxOne: XboxController = RobotMap.XBOX_OI

        var tank: robotDrive = robotDrive()

        val dashManager: DashboardManager = DashboardManager()

        val mouth: Mouth = Mouth()

        val mRobot: Scheduler = Scheduler.getInstance()

        val climb: Climber = Climber()

        lateinit var motion: motionCommand

        val automan: AutonomousManager = AutonomousManager()

//        val test: TestMouth = TestMouth()

        lateinit var oi: OI
    }

//    private lateinit var scheduler: Scheduler

    override fun robotInit() {
        oi = OI()
//        motion = motionCommand(automan.points)/

//        val inst: NetworkTableInstance = NetworkTableInstance.create()
//        val table: NetworkTable = inst.getTable("datatable")
        CameraServer.getInstance().startAutomaticCapture()

        RobotMap.RIGHT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.LEFT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.RIGHT_TALON.setSensorPhase(false)
        RobotMap.LEFT_TALON.setSensorPhase(true)
//        RobotMap.RIGHT_TALON.configOpenloopRamp(.2, 10)
//        RobotMap.LEFT_TALON.configOpenloopRamp(.2, 10)
        RobotMap.RIGHT_TALON.setSelectedSensorPosition(0, 0, 10)
        RobotMap.LEFT_TALON.setSelectedSensorPosition(0, 0, 10)
    }

    override fun disabledInit() {
        RobotMap.COMP.stop()
        RobotMap.RIGHT_TALON.setSelectedSensorPosition(0, 0, 10)
        RobotMap.LEFT_TALON.setSelectedSensorPosition(0, 0, 10)
    }

    override fun autonomousInit() {
//        motion = motionCommand(automan.points)/
        mRobot.removeAll()
//        mRobot.add(motion)
        mRobot.add(WaitCommand(.1))
        //    mRobot.add(DeliverCubeAuto())
        mRobot.enable()
    }

    override fun teleopInit() {
        mRobot.removeAll()
        RobotMap.LEFT_TALON.set(0.0)
        RobotMap.RIGHT_TALON.set(0.0)

    }

    override fun testInit() {
        mRobot.removeAll()
        var group: CommandGroup = DeliverCubeAuto()
        mRobot.add(group)
    }


    override fun disabledPeriodic() {
        RobotMap.RIGHT_TALON.set(0.0)
        RobotMap.LEFT_TALON.set(0.0)
    }

    override fun autonomousPeriodic() {
        RobotMap.COMP.start()
//        motion.init(AutonomousManager().selection())
//        mRobot.add(drive())
//        mRobot.add(drive())
        mRobot.run()
    }

    override fun teleopPeriodic() {

        dashManager.start()
        RobotMap.COMP.start()
        tank.curveDrive(xboxOne) //dddddddddddddddddddddddddddddddddddddddddddddddddddddd
        climb.move(xbox2)

//        var group: CommandGroup = CommandGroup()
//        when {
//            xbox2.yButton -> mouth.stopLips()
//            xbox2.xButton -> group.addSequential(LipsSpit())
//            xbox2.aButton -> group.addSequential(LipsSucc())
//            xbox2.bButton -> mouth.spit()
////            xbox.isLeftTriggerPressed() -> mouth.spit()
////            xbox.xButton -> mouth.moveMouthDown()
////            xbox.yButton -> mouth.moveMouthUp()
////            xbox.xButton -> mouth.succ()
////            xbox.yButton -> mouth.spit()
//        }
//        mRobot.add(group)
        mRobot.run()
    }

    override fun testPeriodic() {

    }
}