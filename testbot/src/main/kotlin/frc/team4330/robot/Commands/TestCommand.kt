package frc.team4330.robot.Commands

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Robot


class TestCommand : Command() {

    init {
        System.out.println("Commanding...")
        requires(Robot.tank)
    }

    override fun execute() {
        System.out.println("Executing")
 //       Robot.tank.curveDrive()
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.tank.stop()
    }


}