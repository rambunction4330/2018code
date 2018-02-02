package frc.team4330.robot.Pathfinder

import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.NavX
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower
import java.io.File

class motion {

    private val gyro: NavX = NavX()
    val fileleft: File = File("test2_left.csv")
    val fileright: File = File("test2_right.csv")

    val rightTraj: Trajectory = Pathfinder.readFromCSV(fileright)
    val leftTraj: Trajectory = Pathfinder.readFromCSV(fileleft)

    var leftFollow: EncoderFollower = EncoderFollower(leftTraj)
    var rightFollow: EncoderFollower = EncoderFollower(rightTraj)

    val l = leftFollow.calculate(RobotMap.leftEncPos)
    val r = rightFollow.calculate(RobotMap.rightEncPos)

    var heading = gyro.angle()
    var desired_headingL = Pathfinder.r2d(leftFollow.heading)

    var angleDifference = Pathfinder.boundHalfDegrees(desired_headingL - heading)
    var turn = .8 * (-1.0 / 80) * angleDifference

    init {
        leftFollow.configureEncoder(RobotMap.leftEncPos, 1024, 0.1016)
        rightFollow.configureEncoder(RobotMap.rightEncPos, 1024, 0.1016)
        leftFollow.configurePIDVA(1.0, 0.0, 0.0, 1.0 / 18.0, 0.0)
        rightFollow.configurePIDVA(1.0, 0.0, 0.0, 1.0 / 18.0, 0.0)

    }
}