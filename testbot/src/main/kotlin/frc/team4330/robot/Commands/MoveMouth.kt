package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot
import frc.team4330.robot.subsystems.Mouth

/*
Toggles intake system between up/down positions.
 */
class MoveMouth : Command() {

    val mouth: Mouth

    init {
        mouth = Mouth()
        requires(mouth)

    }

    override fun execute() {
        Robot.mouth.moveMouthUp()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun end() {
        Robot.mouth.moveMouthDown()
    }
}