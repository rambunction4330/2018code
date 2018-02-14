package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.*

class DeliverCube : CommandGroup() {

    init {
        addSequential(TestCommand(), 0.0)
        addSequential(WaitCommand(1.0))
        addSequential(TestCommand(),0.0)
    }

}