package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Pathfinder.motion
import frc.team4330.robot.Robot
import frc.team4330.robot.subsystems.AutonomousManager

class drive : Command() {

    val motion: motion
    val automan: AutonomousManager

    init {
        motion = motion()
        automan = AutonomousManager()
        setTimeout(8.0)
    }
    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun execute() {

    }

    override fun end() {
        Robot.tank.stop()
    }

}