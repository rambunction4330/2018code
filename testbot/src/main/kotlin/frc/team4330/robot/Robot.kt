package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.cscore.UsbCamera
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.CommandGroups.DeliverCubeAuto
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Pathfinder.motion
import frc.team4330.robot.Pathfinder.motionCommand
import frc.team4330.robot.subsystems.AutonomousManager
import frc.team4330.robot.subsystems.Climber
import frc.team4330.robot.subsystems.Mouth
import frc.team4330.robot.subsystems.robotDrive
import jaci.pathfinder.Trajectory
import openrio.powerup.MatchData

/**
 * @author Sithija Dasun Gunasinghe
 * @author Justin Lam
 * 2018 FRC Power Up code for team 4330
 */

class Robot : TimedRobot() {
    // a companion object is a little different than just using global variables as it can be called throughout the class easily in one organized block
    companion object {

        val xboxOne: XboxController = RobotMap.XBOX_OI //Drive controller

        val xbox2: XboxController = RobotMap.XBOX_INTAKE //mechanism controller

        var tank: robotDrive = robotDrive() //initializes drivetrain class

        val dashManager: DashboardManager = DashboardManager()// initializes dashboard manager which displays sensor data and selectors to shuffleboard

        val mouth: Mouth = Mouth() //ecks to the dee
        // it's the intake
        // this mouth analogy is actually really bad but whatever

        val mRobot: Scheduler = Scheduler.getInstance() // this is the scheduler; you add commands to it that it will execute, simple as that. Don't overthink it.

        val climb: Climber = Climber() // what do you want from me

        var motiongen: motion = motion() // motion profile generator class. It receives an Array of Waypoints and returns a Trajectory
        lateinit var motion: motionCommand // this is a command class that has an input of a Trajectory from above and executesa a motion profile after being initialized

        val automan: AutonomousManager = AutonomousManager() //works with dashboard manager to send data from shuffleboard to the robot

        lateinit var oi: OI // explained below
        // instantiated here so it can be initialized when the robot starts
        // lateinit means it will be set later on but is instantiated here
        // trajectories are instantiated here so they can be initialized later
        lateinit var leftpoints: Trajectory
        lateinit var rightpoints: Trajectory
        lateinit var center_left: Trajectory
        lateinit var center_right: Trajectory
        lateinit var default: Trajectory // "failsafe" trajectory
        lateinit var camera1: UsbCamera
        lateinit var camera2: UsbCamera //initialize a usb camera
    }

//    private lateinit var scheduler: Scheduler

    override fun robotInit() {
        camera1 = CameraServer.getInstance().startAutomaticCapture(0) //Instantiates camera1
        camera2 = CameraServer.getInstance().startAutomaticCapture(1) //does the same thing stop making me document everything

        oi = OI() // Instantiates OI class so that the commands that are associated with buttons actually do things

        //Generates motion profiles when the robot is turned on so it doesn't take like 5 seconds during autonomous
        leftpoints = motiongen.generate(automan.leftpoints)
        rightpoints = motiongen.generate(automan.rightpoints)
        center_left = motiongen.generate(automan.center_left)
        center_right = motiongen.generate(automan.center_right)
        default = motiongen.generate(automan.default)

        // some settings for the drivetrain motor controllers, this is why we use TalonSRXs and VictorSPXs, if y'all don't keep using them I'll come back and haunt you.
        RobotMap.RIGHT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10) // sets encoder for the right drive talon
        RobotMap.LEFT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10) // sets encoder for the other one
        RobotMap.RIGHT_TALON.setSensorPhase(false) // sets whether the output of the encoder is positive or negative, this is is used for when you're going straight and the velocity is negative
        RobotMap.LEFT_TALON.setSensorPhase(true)
        RobotMap.RIGHT_TALON.configOpenloopRamp(.1, 10) // ramps up the voltage so there isn't a jerk when driving
        RobotMap.LEFT_TALON.configOpenloopRamp(.1, 10)
        RobotMap.RIGHT_TALON.setSelectedSensorPosition(0, 0, 10) //sets the encoders to 0 when the robot starts so i dont start with like 1 billion ticks
        RobotMap.LEFT_TALON.setSelectedSensorPosition(0, 0, 10)
    }

    override fun disabledInit() {
        RobotMap.COMP.stop() // disables the pneumatics
        RobotMap.RIGHT_TALON.setSelectedSensorPosition(0, 0, 10) // same as above
        RobotMap.LEFT_TALON.setSelectedSensorPosition(0, 0, 10)
    }

    override fun autonomousInit() {
        RobotMap.gyro.reset() //resets gyro to 0 so the motion profiles execute correctly

        //selects profile to execute during autonomous depending on the position on the field and matchdata
        when (automan.position) {
            0 -> motion = motionCommand(default)
            1 -> motion = motionCommand(leftpoints)
            2 -> when (automan.side) {
                MatchData.OwnedSide.LEFT -> motion = motionCommand(center_left)
                MatchData.OwnedSide.RIGHT -> motion = motionCommand(center_right)
                else -> motion = motionCommand(default)
            }
            3 -> motion = motionCommand(rightpoints)

            else -> motion = motionCommand(default)

        }
        mRobot.removeAll() // makes sure nothing is in the scheduler already
//        mRobot.add(Driveforward()) //drives forward to cross the auto line (Failsafe command)
        mRobot.add(motion) // adds motion command to the command scheduler for it to execute
        mRobot.add(DeliverCubeAuto())
        mRobot.enable() // enables the scheduler so it is primed for autonomous periodic
    }

    override fun teleopInit() {
        mRobot.removeAll() //same as above
        RobotMap.LEFT_TALON.set(0.0) // this is here so the motorcontrollers aren't in a disabled state during teleopperiodic
        RobotMap.RIGHT_TALON.set(0.0)

    }

    override fun testInit() {

    }


    override fun disabledPeriodic() {
        RobotMap.RIGHT_TALON.set(0.0) //same as above, no idea which one works but i dont care enough to test which one actually does stuff
        RobotMap.LEFT_TALON.set(0.0)
    }

    override fun autonomousPeriodic() {
        RobotMap.COMP.start() // starts pneumatics and pressurizes the system, don't forget to close the release valve
        mRobot.run() //runs the scheduler commands set in autoinit
    }

    override fun teleopPeriodic() {
        dashManager.start()
        RobotMap.COMP.start() // same as above
        tank.curveDrive(xboxOne) // this takes an xbox controller and makes the robot drive
        climb.move(xbox2) // climber controls
    }

    override fun testPeriodic() {
        // meme
    }
}