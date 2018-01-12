package frc.team4330.robot.drivebase

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.SpeedController

// Requires CTRE Phoenix Library
class Talon(port: Int) : motors() {
    private val canTalon: WPI_TalonSRX = WPI_TalonSRX(port)

    override var speed: Double
        get() = canTalon.motorOutputPercent
        set(speed) = if (speed !in -1..1 ){
            throw IllegalArgumentException("Speed must be between -1 and 1")
        }
        else{
            canTalon.set(ControlMode.PercentOutput, speed)
        }
    private val wpiTalon: SpeedController = canTalon

}