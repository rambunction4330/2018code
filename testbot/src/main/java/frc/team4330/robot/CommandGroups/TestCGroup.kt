package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import frc.team4330.robot.Commands.TestCommand
import frc.team4330.robot.Commands.TestCommand2

class TestCGroup(position: Int) : CommandGroup() {

    init {
        when(position) {
            1 -> addSequential( TestCommand(), 0.5)
            2 -> addSequential( WaitCommand(3.0))
            3 -> addSequential( TestCommand2(), 0.5)
        }
    }
}