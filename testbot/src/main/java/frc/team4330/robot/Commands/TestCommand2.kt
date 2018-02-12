package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot

class TestCommand2 : Command(){

    init {
        setTimeout(0.9)
    }

    override fun execute() {
        Robot.mDrive.autoDrive(-0.3, 0.0,false)
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.mDrive.stop()
    }
}