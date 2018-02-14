package frc.team4330.robot.Commands

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Robot


class TestCommand : Command() {

    init {
        requires(Robot.tank)
        setTimeout(2.0)
    }

    override fun execute() {
        Robot.tank.driveForward(0.1, 0.0, false)
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.tank.stop()
    }


}