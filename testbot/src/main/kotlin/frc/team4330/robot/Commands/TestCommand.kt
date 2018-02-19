package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot


class TestCommand : Command() {

    init {
        setTimeout(2.0)
        requires(Robot.tank)
    }

    override fun execute() {
        Robot.tank.driveForward(-0.3, 0.0, false)
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.tank.stop()
    }


}