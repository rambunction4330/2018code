package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class TestJaw : Command() {

    init {
        requires(Robot.mouth)
        setTimeout(5.0)
    }

    override fun execute() {
        Robot.mouth.moveMouthUp()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        super.end()
    }
}