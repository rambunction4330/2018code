package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.Mouth

class stopLips : Command() {

    val mouth: Mouth

    init {
        mouth = Mouth()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
        mouth.stopLips()
    }
}