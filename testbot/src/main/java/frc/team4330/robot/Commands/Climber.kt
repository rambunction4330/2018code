package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.Input
import frc.team4330.robot.subsystems.prototypes

class Climber : Command() {

    val climb: prototypes

    init {
        climb = prototypes()
    }


    override fun initialize() {
        super.initialize()
        climb.move(xbox = Input(0))
    }

    override fun isFinished(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}