package frc.team4330.robot.Pathfinder

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.robotDrive
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

class motionCommand : Command {
    override fun isFinished(): Boolean {
        return leftFollow.isFinished && rightFollow.isFinished
    }

    var config: Trajectory.Config
    var points: Array<Waypoint>
    var trajectory: Trajectory
    var modifier: TankModifier
    var left: Trajectory
    var right: Trajectory
    var leftFollow: EncoderFollower
    var rightFollow: EncoderFollower
    var mDrive: robotDrive


    constructor(make: Array<Waypoint>) {
        mDrive = robotDrive()
        config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.5, 2.0, 60.0)
        points = arrayOf(Waypoint(0.0, -0.0, 0.0), Waypoint(1.0, 0.0, 0.0), Waypoint(2.5, 0.0, 0.0))
        if (make.size != 0 || make != null) {
            points = make
        }
        trajectory = Pathfinder.generate(points, config)
        modifier = TankModifier(trajectory).modify(.5)
        left = modifier.leftTrajectory
        right = modifier.rightTrajectory
        left = modifier.leftTrajectory
        right = modifier.rightTrajectory
        leftFollow = EncoderFollower(left)
        rightFollow = EncoderFollower(right)
        leftFollow.configureEncoder(RobotMap.leftEncPos, 4096 * Constants.ENCODER.toInt(), .1016)
        rightFollow.configureEncoder(RobotMap.rightEncPos, 4096 * Constants.ENCODER.toInt(), .1016)
        leftFollow.configurePIDVA(1.0, 0.0, .01, 1 / 1.8, 0.0)
        rightFollow.configurePIDVA(1.0, 0.0, .01, 1 / 1.8, 0.0)
    }

    override fun execute() {
        var l = leftFollow.calculate(RobotMap.leftEncPos)
        var r = rightFollow.calculate(RobotMap.rightEncPos)
        var gyro_heading = RobotMap.gyro.angle
        var desired_heading = Pathfinder.r2d(leftFollow.heading)
        var angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading)
        var turn = 0.8 * (-1.0 / 80) * angleDifference

        RobotMap.RIGHT_TALON.set(ControlMode.PercentOutput, r - turn)
        RobotMap.LEFT_TALON.set(ControlMode.PercentOutput, -(l + turn))
    }

    override fun end() {
        mDrive.stop()
    }
}