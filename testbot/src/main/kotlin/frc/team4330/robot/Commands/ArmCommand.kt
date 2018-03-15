package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.NidecArm

class ArmCommand : Command {
    var armboi = NidecArm()
    constructor() {
        requires(NidecArm())
        setTimeout(.5)

    }

    override fun execute() {
        armboi.forwards()
    }

    override fun isFinished(): Boolean {
        return false
    }
}