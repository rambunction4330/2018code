package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.PhaseCompleteCommand
import frc.team4330.robot.Commands.ZeroPhaseCommand
import frc.team4330.robot.DashboardManager
import frc.team4330.robot.util.AutonomousPhase


class AutonomousManager {

    var phase: AutonomousPhase = AutonomousPhase.ONE
    var position = DashboardManager.positionChooser.selected
    var scheduler: Scheduler = Scheduler.getInstance()

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


}