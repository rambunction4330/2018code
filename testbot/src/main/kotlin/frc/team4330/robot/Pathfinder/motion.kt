package frc.team4330.robot.Pathfinder

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.SubsystemBase
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower


class motion : SubsystemBase() {

    fun init(paths: Array<Trajectory>) {

//        val fileLeft = File("pos1_left.csv")
//        val left = Pathfinder.readFromCSV(fileLeft)
//        val fileRight = File("pos1_right.csv")
//        val right = Pathfinder.readFromCSV(fileRight)
//
//        var config: Trajectory.Config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.8, 2.0, 60.0)
//        var points = arrayOf(Waypoint(0.0, 20.0, 0.0), Waypoint(7.0, 23.0, 0.0), Waypoint(14.0, 20.0, Pathfinder.d2r(-90.0)))
//        var trajectory: Trajectory = Pathfinder.generate(points, config)
//        var modifier: TankModifier = TankModifier(trajectory).modify(.5)
        var left: Trajectory = paths.get(1)
        var right: Trajectory = paths.get(0)

        var leftFollow: EncoderFollower = EncoderFollower(left)
        var rightFollow: EncoderFollower = EncoderFollower(right)
        leftFollow.configureEncoder(RobotMap.leftEncPos, 4096, Constants.ENCODER)
        rightFollow.configureEncoder(RobotMap.rightEncPos, 4096, Constants.ENCODER)
        leftFollow.configurePIDVA(1.0, 0.0, .01, 1 / 1.8, 0.0)
        rightFollow.configurePIDVA(1.0, 0.0, .01, 1 / 1.8, 0.0)

        for (i in right.segments) {
            var l = leftFollow.calculate(RobotMap.leftEncPos)
            var r = rightFollow.calculate(RobotMap.rightEncPos)
            var gyro_heading = RobotMap.gyro.angle
            var desired_heading = Pathfinder.r2d(leftFollow.heading)
            var angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading)
            var turn = 0.8 * (-1.0 / 80) * angleDifference

            RobotMap.RIGHT_TALON.set(ControlMode.PercentOutput, -(r - turn))
            RobotMap.LEFT_TALON.set(ControlMode.PercentOutput, l + turn)
            WaitCommand(.05)
        }
    }
}