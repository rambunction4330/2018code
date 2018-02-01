package frc.team4330.robot.drivebase

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX

class Talon(port: Int) : motors() {
    private val canTalon: TalonSRX = TalonSRX(port)

    override var speed: Double
        get() = canTalon.motorOutputPercent
        set(speed) = if (speed !in -1..1 ){
            throw IllegalArgumentException("Speed must be between -1 and 1")
        }
        else{
            canTalon.set(ControlMode.PercentOutput, speed)
        }
}