package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
//import frc.team4330.robot.subsystems.AutonomousManager
import frc.team4330.robot.util.AutonomousPhase

class PhaseCompleteCommand : Command() {

    private lateinit var phase: AutonomousPhase

    fun init(phase: AutonomousPhase) {
        this.phase = phase
    }

    override fun execute() {
        //       Robot.steveBannon.phase = this.phase
    }

    override fun isFinished(): Boolean {
        return true
    }
}