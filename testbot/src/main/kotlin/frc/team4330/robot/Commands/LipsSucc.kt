package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class LipsSucc : Command() {
    init {
        requires(Robot.mouth)
        //  setTimeout(2.0)
    }

    override fun execute() {
        Robot.mouth.succ()
    }

    override fun isFinished(): Boolean {
        return false//isTimedOut//!Robot.xbox2.yButtonPressed
    }

    override fun end() {
        Robot.mouth.stopLips()
    }
}