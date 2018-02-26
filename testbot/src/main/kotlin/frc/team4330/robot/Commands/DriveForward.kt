package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Pathfinder.motion

class DriveForward : Command() {

    companion object {
        val motion: motion = motion()
    }

    override fun isFinished(): Boolean {
        return false
    }

}