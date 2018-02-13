package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.InIntake
import frc.team4330.robot.Commands.OutIntake

class DeliverCube : CommandGroup() {

    init {
        addSequential(InIntake, 0.5)
        addSequential(WaitCommand(0.5))
        addSequential(OutIntake,0.0)
    }

}