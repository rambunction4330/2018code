package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team4330.robot.Commands.ArmCommand
import frc.team4330.robot.Commands.BackHand

class armboi : CommandGroup {
    constructor() {
        addSequential(ArmCommand())
        addSequential(BackHand())
    }
}