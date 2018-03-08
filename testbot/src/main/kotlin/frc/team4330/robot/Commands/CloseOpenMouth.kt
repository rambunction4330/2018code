package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class CloseOpenMouth : Command() {
    init {
        requires(Robot.mouth)
    }

    override fun execute() {
        Robot.mouth.succ()
        Robot.mouth.closeMouth()
        print("OPEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeN")
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun end() {
        Robot.mouth.openWide()
        Robot.mouth.stopLips()
    }
}