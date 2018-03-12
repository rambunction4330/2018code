package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.robotDrive

/*
Toggle between robot gears. Shifts up/down.
 */

class shiftup : Command() {

    companion object {
        val mDrive = robotDrive()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
        mDrive.upShift()
    }

    override fun end() {
        mDrive.downShift()
    }

}