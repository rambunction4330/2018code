package frc.team4330.robot.Pathfinder

import frc.team4330.robot.IO.RobotMap
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower
import java.io.File

class motion {


    val fileleft: File = File("test2_left.csv")
    val fileright: File = File("test2_right.csv")

    val rightTraj: Trajectory = Pathfinder.readFromCSV(fileright)
    val leftTraj: Trajectory = Pathfinder.readFromCSV(fileleft)

    val leftFollow: EncoderFollower = EncoderFollower(leftTraj)
    val rightFollow: EncoderFollower = EncoderFollower(rightTraj)

    init {
        leftFollow.configureEncoder(RobotMap.leftEncPos, 1024, 0.1016)
        rightFollow.configureEncoder(RobotMap.rightEncPos, 1024, 0.1016)
        leftFollow.configurePIDVA(1.0, 0.0, 0.0, 1.0 / 18.0, 0.0)
        rightFollow.configurePIDVA(1.0, 0.0, 0.0, 1.0 / 18.0, 0.0)

    }
}