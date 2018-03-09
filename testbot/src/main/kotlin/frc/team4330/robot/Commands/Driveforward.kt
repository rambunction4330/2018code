package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.robotDrive

class Driveforward : Command() {

    val drive: robotDrive
    var finished: Boolean

    init {
        drive = robotDrive()
        finished = false
    }

    override fun isFinished(): Boolean {
        return finished
    }

    override fun execute() {
//        var initial = (RobotMap.rightEncPos + RobotMap.leftEncPos)/2
        drive.driveForward(.5, 0.0, false)
        if (((RobotMap.leftEncPos + RobotMap.rightEncPos) / 2) * Constants.ENCODER * 4096 == 3.06)
            finished = true
    }



}