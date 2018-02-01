package frc.team4330.robot.drivebase

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.VictorSPX

class Victor(port: Int) : motors() {
    private val pwmVictor: VictorSPX = VictorSPX(port)

    override var speed: Double
        get() = pwmVictor.motorOutputPercent
        set(speed) = if (speed !in -1..1) {
            throw IllegalArgumentException("Speed must be between -1 and 1")
        } else {
            pwmVictor.set(ControlMode.PercentOutput, speed)
        }
}