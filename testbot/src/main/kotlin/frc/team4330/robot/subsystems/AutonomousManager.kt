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
import openrio.powerup.MatchData
import java.io.File


class AutonomousManager : SubsystemBase() {

    var phase: AutonomousPhase = AutonomousPhase.ONE
    var position = DashboardManager.positionChooser.selected
    var scheduler: Scheduler = Scheduler.getInstance()
    val side: MatchData.OwnedSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR)

    var left_switchRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/left_switch_right.csv"))
    var left_switchLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/left_switch_left.csv"))

    var right_switchRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/right_switch_right.csv"))
    var right_switchLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/right_switch_left.csv"))

    var center_leftLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_left_left.csv"))
    var center_leftRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_left_right.csv"))

    var center_rightLeft = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_right_left.csv"))
    var center_rightRight = Pathfinder.readFromCSV(File("/home/lvuser/paths/center_right_right.csv"))


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

    fun selection(): Array<Trajectory> {
        when (position) {
            1 -> return arrayOf(left_switchRight, left_switchLeft)

            2 -> when (side) {
                MatchData.OwnedSide.RIGHT -> return arrayOf(center_rightRight, center_rightLeft)
                MatchData.OwnedSide.LEFT -> return arrayOf(center_leftRight, center_leftLeft)
            }

            3 -> return arrayOf(right_switchRight, right_switchLeft)
        }
        return arrayOf(left_switchLeft, left_switchLeft)
    }



}