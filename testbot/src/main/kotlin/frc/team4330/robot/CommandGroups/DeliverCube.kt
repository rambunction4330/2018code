package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.InIntake
import frc.team4330.robot.Commands.OutIntake
import frc.team4330.robot.Commands.TestJaw

class DeliverCube : CommandGroup() {

    init {
        addSequential(TestJaw(), 0.0)
        addSequential(WaitCommand(0.5))
        addSequential(TestJaw(),0.0)
    }

}