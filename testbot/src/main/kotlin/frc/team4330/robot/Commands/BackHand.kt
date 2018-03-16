package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team4330.robot.IO.RobotMap

class BackHand : Command {
    val armboi = RobotMap.nidecMotor

    constructor() {
        setTimeout(.5)
    }

    override fun execute() {
        armboi.set(-.2)
    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

}