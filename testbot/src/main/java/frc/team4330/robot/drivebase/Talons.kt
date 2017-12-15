package frc.team4330.robot.drivebase

import com.ctre.CANTalon

class Talons (port: Int) : motors(){
    private val canTalon: CANTalon = CANTalon(port)


    override var speed: Double
        get() = canTalon.get()
        set(speed) = if (speed !in -1..1 ){
            throw IllegalArgumentException("Speed must be between -1 and 1")
        }
        else{
            canTalon.set(speed)
        }
}