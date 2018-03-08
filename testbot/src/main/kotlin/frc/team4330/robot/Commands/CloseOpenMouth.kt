package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class CloseOpenMouth : Command() {
    init {
        requires(Robot.mouth)
        setTimeout(0.3)
    }

    override fun execute() {
        Robot.mouth.openClose()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }
}