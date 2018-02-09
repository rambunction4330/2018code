package frc.team4330.robot.IO

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import edu.wpi.first.wpilibj.Encoder

class Encoders(A: Int, B: Int) {

    init {
        RobotMap.RIGHT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.LEFT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
    }

    private val encoder: Encoder = Encoder(A, B)

    var amount: Int = 0
        get() = encoder.get()

    fun reset() = encoder.reset()


}