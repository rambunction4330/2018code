package frc.team4330.robot.IO

import edu.wpi.first.wpilibj.Encoder

class Encoders(A: Int, B: Int) {

    private val encoder: Encoder = Encoder(A, B)

    var amount: Int = 0
        get() = encoder.get()

    fun reset() = encoder.reset()


}