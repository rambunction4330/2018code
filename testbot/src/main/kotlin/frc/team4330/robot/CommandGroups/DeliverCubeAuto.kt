package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.LipsSpit
import frc.team4330.robot.Commands.OutIntake

class DeliverCubeAuto : CommandGroup() {
    init {
        addSequential(OutIntake())
        addSequential(WaitCommand(0.5))
        addSequential(LipsSpit(), 1.0)
        addSequential(WaitCommand(2.0))
    }
}