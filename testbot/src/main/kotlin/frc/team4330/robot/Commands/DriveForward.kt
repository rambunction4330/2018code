package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.Pathfinder.motion

class DriveForward : Command() {

    companion object {
        val motion: motion = motion()
    }

    override fun start() {
        super.start()
        motion.init()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
        super.execute()
        motion.test()

    }

}