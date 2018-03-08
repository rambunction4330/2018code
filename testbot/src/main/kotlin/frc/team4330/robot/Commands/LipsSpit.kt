package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class LipsSpit : Command() {
    init {
        requires(Robot.mouth)
    }

    override fun execute() {
        Robot.mouth.spit()
    }

    override fun isFinished(): Boolean {
        return !Robot.xbox2.xButtonPressed
    }

    override fun end() {
        Robot.mouth.stopLips()
    }
}