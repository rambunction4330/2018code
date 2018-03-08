package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class MoveMouth : Command() {
    init {
        requires(Robot.mouth)
        setTimeout(0.3)
    }

    override fun execute() {
        Robot.mouth.moveMouth()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }
}