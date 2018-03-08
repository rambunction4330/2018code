package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class LipsSucc : Command() {
    init {
        requires(Robot.mouth)
    }

    override fun execute() {
        Robot.mouth.succ()
    }

    override fun isFinished(): Boolean {
        return !Robot.xbox2.yButtonPressed
    }

    override fun end() {
        Robot.mouth.stopLips()
    }
}