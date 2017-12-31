package frc.team4330.robot.drivebase

import edu.wpi.first.wpilibj.VictorSP

class Victor(port: Int) : motors() {
    private val pwmVictor: VictorSP = VictorSP(port)

    override var speed: Double
        get() = pwmVictor.get()
        set(speed) = if (speed !in -1..1) {
            throw IllegalArgumentException("Speed must be between -1 and 1")
        } else {
            pwmVictor.set(speed)
        }
}