package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.LipsSpit
import frc.team4330.robot.Commands.OutIntake
import frc.team4330.robot.Commands.drive

class DeliverCubeAuto : CommandGroup() {
    init {
        addSequential(drive())
        addSequential(WaitCommand(0.1))
        addSequential(OutIntake())
        addSequential(WaitCommand(0.5))
        addSequential(LipsSpit(), 1.0)
        addSequential(WaitCommand(0.5))
    }
}