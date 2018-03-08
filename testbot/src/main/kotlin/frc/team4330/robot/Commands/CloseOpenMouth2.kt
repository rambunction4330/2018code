package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class CloseOpenMouth2 : Command() {
    init {
        requires(Robot.mouth)
    }

    override fun execute() {
        Robot.mouth.openWide()
        Robot.mouth.succ()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun end() {
        Robot.mouth.closeMouth()
        Robot.mouth.stopLips()
    }
}