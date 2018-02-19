package frc.team4330.robot.Pathfinder

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.SubsystemBase
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

class motion : SubsystemBase() {

//    var points = arrayOf(Waypoint(-4.0, -2.0, Pathfinder.d2r(-45.0)), Waypoint(-2.0, -2.0, 0.0), Waypoint(0.0, 0.0, 0.0))
//    val config: Trajectory.Config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, .05, 1.8, 2.0, 60.0)
//    lateinit var trajectory: Trajectory
//    lateinit var modifier: TankModifier
//    lateinit var left: EncoderFollower
//    lateinit var right: EncoderFollower
//
//    fun init() {
//
//        trajectory = Pathfinder.generate(points, config)
//
//        modifier = TankModifier(trajectory)
//        left = EncoderFollower(modifier.leftTrajectory)
//        right = EncoderFollower(modifier.rightTrajectory)
//        left.setTrajectory(modifier.leftTrajectory)
//        right.setTrajectory(modifier.rightTrajectory)
//
//    }
//
//    fun test() {
//
//        print("test")
//        RobotMap.LEFT_TALON.set(ControlMode.Velocity, left.segment.velocity)
//        RobotMap.RIGHT_TALON.set(ControlMode.Velocity, right.segment.velocity)
//        print(left.segment.velocity)
//        print(right.segment.velocity)
//    }

//    fun move() {
//
//
////        var output = left.calculate(RobotMap.leftEncPos.toInt())
//        var l = left.calculate(RobotMap.leftEncPos)
//        var r = right.calculate(RobotMap.rightEncPos)
//        var gyro_heading = RobotMap.gyro.angle
//        var desired_heading = Pathfinder.r2d(left.heading)
//
//        var angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading)
//        var turn = 0.8 * (-1.0 / 80.0) * angleDifference
//
//        RobotMap.RIGHT_TALON.set(r - turn)
//        RobotMap.LEFT_TALON.set(l + turn)
//    }

    fun init() {
        var config: Trajectory.Config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.8, 2.0, 60.0)
        var points = arrayOf(Waypoint(-4.0, -2.0, Pathfinder.d2r(-45.0)), Waypoint(-2.0, -2.0, 0.0), Waypoint(0.0, 0.0, 0.0))
        var trajectory: Trajectory = Pathfinder.generate(points, config)
        var modifier: TankModifier = TankModifier(trajectory).modify(.5)
        var left: Trajectory = modifier.leftTrajectory
        var right: Trajectory = modifier.rightTrajectory
        left = modifier.leftTrajectory
        right = modifier.rightTrajectory
        var leftFollow: EncoderFollower = EncoderFollower(left)
        var rightFollow: EncoderFollower = EncoderFollower(right)
        leftFollow.configureEncoder(RobotMap.leftEncPos, 4096, .1016)
        rightFollow.configureEncoder(RobotMap.rightEncPos, 4096, .1016)
        leftFollow.configurePIDVA(1.0, 0.0, .01, 1 / 1.8, 0.0)
        rightFollow.configurePIDVA(1.0, 0.0, .01, 1 / 1.8, 0.0)


//        for (i in left.segments)
//            i.velocity
        for (i in trajectory.segments) {
            var l = leftFollow.calculate(RobotMap.leftEncPos)
            var r = rightFollow.calculate(RobotMap.rightEncPos)
            var gyro_heading = RobotMap.gyro.angle
            var desired_heading = Pathfinder.r2d(leftFollow.heading)
            var angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading)
            var turn = 0.8 * (-1.0 / 80) * angleDifference

            RobotMap.RIGHT_TALON.set(ControlMode.PercentOutput, r - turn)
            RobotMap.LEFT_TALON.set(ControlMode.PercentOutput, l + turn)
        }


        

    }


}