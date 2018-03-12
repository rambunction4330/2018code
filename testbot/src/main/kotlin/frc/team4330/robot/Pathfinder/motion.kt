package frc.team4330.robot.Pathfinder

import frc.team4330.robot.subsystems.SubsystemBase
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint


class motion : SubsystemBase() {

    fun generate(points: Array<Waypoint>): Trajectory {

//        val fileLeft = File("pos1_left.csv")
//        val left = Pathfinder.readFromCSV(fileLeft)
//        val fileRight = File("pos1_right.csv")
//        val right = Pathfinder.readFromCSV(fileRight)
//
        var config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.5, 2.0, 60.0)
//        var points = arrayOf(Waypoint(0.0, 20.0, 0.0), Waypoint(7.0, 23.0, 0.0), Waypoint(14.0, 20.0, Pathfinder.d2r(-90.0)))
        var trajectory: Trajectory = Pathfinder.generate(points, config)

        return trajectory





        

    }


}