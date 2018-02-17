package frc.team4330.robot.subsystems

import frc.team4330.robot.util.AutonomousPhase

class AutonomousManager {

    var phase: AutonomousPhase()

    fun init{
        phase = AutonomousPhase.ONE
    }


}