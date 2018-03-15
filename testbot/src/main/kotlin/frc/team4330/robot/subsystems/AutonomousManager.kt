package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.command.Scheduler
import frc.team4330.robot.DashboardManager
import frc.team4330.robot.util.AutonomousPhase
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import openrio.powerup.MatchData


class AutonomousManager : SubsystemBase() {

    var phase: AutonomousPhase = AutonomousPhase.ONE
//    var position = DashboardManager.ge
    var scheduler: Scheduler = Scheduler.getInstance()
    val side: MatchData.OwnedSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR)

    //    var left_switchRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/left_switch_right.csv"))
//    var left_switchLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/left_switch_left.csv"))
//
//    var right_switchRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/right_switch_right.csv"))
//    var right_switchLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/right_switch_left.csv"))
//
//    var center_leftLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_left_left.csv"))
//    var center_leftRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_left_right.csv"))
//
//    var center_rightLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_right_left.csv"))
//    var center_rightRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_right_right.csv"))
    val config: Trajectory.Config = Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.8, 2.0, 60.0)
    var points = arrayOf(Waypoint(0.0, 20.0, 0.0), Waypoint(7.0, 23.0, 0.0), Waypoint(14.0, 20.0, Pathfinder.d2r(-90.0)))


    var leftpoints = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.3, -0.5, 0.0), Waypoint(2.0, -.8, Pathfinder.d2r(90.0)))
    var center_left = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(3.0, .8, Pathfinder.d2r(45.0)), Waypoint(6.0, 1.2, 0.0))
    var center_right = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.3, -.8, Pathfinder.d2r(45.0)), Waypoint(2.0, 1.2, 0.0))
    var rightpoints = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.3, .5, 0.0), Waypoint(2.0, .8, Pathfinder.d2r(-90.0)))
    var default = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(4.0, 0.0, 0.0), Waypoint(6.0, 0.0, 0.0))



    init {
//        phase = AutonomousPhase.ONE

//        val group = CommandGroup()
//        group.addSequential(ZeroPhaseCommand())
//        when (position) {
//            1 -> println("Going to the LEFT.")
//            2 -> println("Going to the RIGHT.")
//            3 -> println("Going to the MIDDLE.")
//        }


//        scheduler.add(group)
//        scheduler.enable()
    }






}