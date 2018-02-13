package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Robot
import frc.team4330.robot.subsystems.Mouth

class InIntake : Command() {

    init {
        requires(Robot.mouth)
        setTimeout(2.0)
    }

    override fun execute() {
        Robot.mouth.openWide()
        Robot.mouth.succ()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.mouth.stopLips()
    }
}