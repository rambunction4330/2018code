package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.Mouth

class LipsSucc : Command() {
    val mouth: Mouth
    init {
        mouth = Mouth()
        requires(mouth)
        setTimeout(1.5)
    }

    override fun execute() {
        mouth.succ()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        mouth.stopLips()
    }
}