package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.Driveforward

class Tempclass : CommandGroup() {
    init {
        addSequential(WaitCommand(7.0))
        addSequential(Driveforward())
    }
}