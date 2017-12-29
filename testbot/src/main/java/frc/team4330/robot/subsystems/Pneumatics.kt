package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.Compressor

class Pneumatics(canID: Int) : SubsystemBase() {

    val comp: Compressor

    init {
        comp = Compressor(canID)
    }

    fun init() {
        comp.start()

    }


}