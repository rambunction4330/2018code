package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

/*
Toggles Intake between open/close position.
 */
class CloseOpenMouth : Command() {
    init {
        requires(Robot.mouth)
    }

    override fun execute() {
        Robot.mouth.closeMouth()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun end() {
        Robot.mouth.openWide()
    }
}