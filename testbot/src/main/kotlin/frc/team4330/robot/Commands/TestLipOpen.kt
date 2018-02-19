package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class TestLipOpen : Command(){
    init {
        requires(Robot.mouth)
        setTimeout(2.0)
    }

    override fun execute() {
        Robot.mouth.openWide()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

}