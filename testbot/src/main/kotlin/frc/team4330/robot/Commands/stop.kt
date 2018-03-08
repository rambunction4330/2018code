package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.RobotMap

class stop : Command() {
    override fun isFinished(): Boolean {
        return false
    }

    override fun end() {

    }

    override fun execute() {
        RobotMap.LEFT_TALON.set(0.0)
        RobotMap.RIGHT_TALON.set(0.0)
    }


}