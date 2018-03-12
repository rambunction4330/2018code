package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.robotDrive

class Driveforward : Command() {

    val drive: robotDrive
    var finished: Boolean

    init {
        drive = robotDrive()
        finished = false
        setTimeout(4.0)
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun execute() {
//        var initial = (RobotMap.rightEncPos + RobotMap.leftEncPos)/2
        drive.driveForward(-.7, 0.0, false)

    }
}