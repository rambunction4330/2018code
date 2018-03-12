package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.TestCommand
import frc.team4330.robot.Commands.drive

class DeliverCube : CommandGroup() {

    init {
        addSequential(drive())
        addSequential(TestCommand(), 1.0)
        addSequential(WaitCommand(1.0))
        addSequential(TestCommand(), 1.0)
        addSequential(WaitCommand(2.0))
    }
}