package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.PhaseCompleteCommand
import frc.team4330.robot.Commands.ZeroPhaseCommand
import frc.team4330.robot.DashboardManager
import frc.team4330.robot.util.AutonomousPhase
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.modifiers.TankModifier
import openrio.powerup.MatchData


class AutonomousManager : SubsystemBase() {

    var phase: AutonomousPhase = AutonomousPhase.ONE
    var position = DashboardManager.positionChooser.selected
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




    init {
        phase = AutonomousPhase.ONE

        val group = CommandGroup()
        group.addSequential(ZeroPhaseCommand())
        when (position) {
            1 -> println("Going to the LEFT.")
            2 -> println("Going to the RIGHT.")
            3 -> println("Going to the MIDDLE.")
        }



        group.addSequential(WaitCommand(0.5))
        group.addSequential(PhaseCompleteCommand(), 0.5)
        scheduler.add(group)
        scheduler.enable()
    }


    fun selection(): TankModifier {
        when (position) {
            1 -> points = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.5, -0.5, 0.0), Waypoint(3.3, -.8, Pathfinder.d2r(-90.0)))

            2 -> when (side) {
                MatchData.OwnedSide.LEFT -> points = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.5, .8, Pathfinder.d2r(45.0)), Waypoint(3.2, 1.2, 0.0))
                MatchData.OwnedSide.RIGHT -> points = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.5, -.8, Pathfinder.d2r(45.0)), Waypoint(3.2, 1.2, 0.0))
            }
            3 -> points = arrayOf(Waypoint(0.0, 0.0, 0.0), Waypoint(1.5, .5, 0.0), Waypoint(3.3, .8, Pathfinder.d2r(90.0)))
        }
        var trajectory: Trajectory = Pathfinder.generate(points, config)

        var modifier: TankModifier = TankModifier(trajectory).modify(.61)
        return modifier


    }



}