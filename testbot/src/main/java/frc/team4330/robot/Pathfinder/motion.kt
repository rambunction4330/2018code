package frc.team4330.robot.Pathfinder

import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.followers.EncoderFollower
import java.io.File

class motion {


    val fileleft: File = File("test2_left.csv")
    val fileright: File = File("test2_right.csv")

    val rightTraj: Trajectory = Pathfinder.readFromCSV(fileright)
    val leftTraj: Trajectory = Pathfinder.readFromCSV(fileleft)

    val leftFollow: EncoderFollower = EncoderFollower()
}