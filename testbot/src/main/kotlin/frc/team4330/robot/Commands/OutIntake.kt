package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class OutIntake : Command() {

    init {
        requires(Robot.mouth)
        setTimeout(2.0)
    }

    override fun execute() {
        Robot.mouth.moveMouthUp()
        Robot.mouth.spit()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.mouth.openWide()
        Robot.mouth.stopLips()
    }
}