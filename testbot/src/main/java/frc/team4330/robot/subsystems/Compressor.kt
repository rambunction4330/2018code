package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.Compressor

class Compressor(canID: Int) : SubsystemBase() {

    val comp: Compressor

    init {
        comp = Compressor(canID)
    }

    fun init() {
        comp.start()
    }

    fun stop() {
        comp.stop()
    }
}