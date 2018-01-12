package frc.team4330.robot.drivebase

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.IMotorController
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
// Requires CTRE Phoenix Library
class Victor(port: Int) : motors() {
    private val Victor: WPI_VictorSPX = WPI_VictorSPX(port)

    override var speed: Double
        get() = Victor.get()
        set(speed) = if (speed !in -1..1) {
            throw IllegalArgumentException("Speed must be between -1 and 1")
        } else {
            Victor.set(ControlMode.Velocity, speed)

        }
    fun follow(masterToFollow :IMotorController){
        Victor.follow(masterToFollow)
    }
}