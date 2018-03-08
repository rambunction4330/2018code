package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.robotDrive

class shiftdown : Command() {

    companion object {
        val mDrive = robotDrive()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
        mDrive.downShift()
    }
}