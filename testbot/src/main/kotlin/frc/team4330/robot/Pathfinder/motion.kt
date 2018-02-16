package frc.team4330.robot.Pathfinder

import frc.team4330.robot.IO.RobotMap
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier

class motion {

    var points = arrayOf(Waypoint(-4.0, -2.0, Pathfinder.d2r(-45.0)), Waypoint(-2.0, -2.0, 0.0), Waypoint(0.0, 0.0, 0.0))
    val config: Trajectory.Config
    val trajectory: Trajectory
    val modifier: TankModifier
    var left: EncoderFollower
    var right: EncoderFollower

    init {
        config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, .05, 1.8, 2.0, 60.0)
        trajectory = Pathfinder.generate(points, config)
        modifier = TankModifier(trajectory)
        left = EncoderFollower(modifier.leftTrajectory)
        right = EncoderFollower(modifier.rightTrajectory)
        left.configureEncoder(RobotMap.leftEncPos, 1024, .1016)
        right.configureEncoder(RobotMap.rightEncPos, 1024, .1016)
        left.configurePIDVA(1.0, 0.0, 0.0, 1 / 6.0, 0.0)
        right.configurePIDVA(1.0, 0.0, 0.0, 1 / 6.0, 0.0)
    }

    fun move() {

//        var output = left.calculate(RobotMap.leftEncPos.toInt())
        var l = left.calculate(RobotMap.leftEncPos)
        var r = right.calculate(RobotMap.rightEncPos)
        var gyro_heading = RobotMap.gyro.angle
        var desired_heading = Pathfinder.r2d(left.heading)

        var angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading)
        var turn = 0.8 * (-1.0 / 80.0) * angleDifference

        RobotMap.RIGHT_TALON.set(r - turn)
        RobotMap.LEFT_TALON.set(l + turn)
    }


}