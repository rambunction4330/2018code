package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Robot
/*
Simple command that drives robot forward. Used to test how commands work since no one knows/would help me. Cri eveytim
 */

class TestCommand : Command() {

    init {
        System.out.println("Commanding...")
        setTimeout(0.9)
    }

    override fun execute() {
        System.out.println("Executing...")
        Robot.mDrive.autoDrive(0.2, 1.0,false)
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        Robot.mDrive.stop()
    }
}