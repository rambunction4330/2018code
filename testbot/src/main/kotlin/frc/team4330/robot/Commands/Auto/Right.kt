package frc.team4330.robot.Pathfinder

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Robot
import frc.team4330.robot.subsystems.robotDrive
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

/**
 * @author Sithija Dasun Gunasinghe
 * Motion Profiling command using Jaci's Pathfinder
 * https://github.com/JacisNonsense/Pathfinder
 */
class Right : Command {
    override fun isFinished(): Boolean {
        return leftFollow.isFinished && rightFollow.isFinished
    }

    var modifier: TankModifier
    var left: Trajectory
    var right: Trajectory
    var leftFollow: EncoderFollower
    var rightFollow: EncoderFollower
    var mDrive: robotDrive


    constructor() {

        mDrive = robotDrive()


        modifier = TankModifier(Robot.rightpoints).modify(.5)
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
        println("righttest")
    }

    override fun end() {
        mDrive.stop()
    }
}