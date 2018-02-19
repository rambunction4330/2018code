package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.InIntake
import frc.team4330.robot.Commands.OutIntake

class TestMouth : CommandGroup() {
    init {
        addSequential(InIntake(), 1.0)
        addSequential(WaitCommand(1.0))
        addSequential(OutIntake(), 1.0)
        addSequential(WaitCommand(1.0))
    }
}