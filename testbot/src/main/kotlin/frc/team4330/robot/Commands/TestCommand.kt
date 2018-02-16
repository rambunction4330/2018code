package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot


class TestCommand : Command() {

    init {
        requires(Robot.tank)
        setTimeout(5.0)
    }

    override fun execute() {
        print("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
        Robot.tank.driveForward(0.5, 0.0, false)
    }

    override fun isFinished(): Boolean {
        print("IT JUST TIMED OUT")
        return isTimedOut
    }

    override fun end() {
        Robot.tank.stop()
    }


}