package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

/*
Command for autonomous
 */
class OutIntake : Command() {

    init {
        requires(Robot.mouth)
        setTimeout(6.0)
    }

    override fun execute() {
        Robot.mouth.moveMouthUp()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        //  Robot.mouth.moveMouthDown()
        //  Robot.mouth.openWide()
    }
}