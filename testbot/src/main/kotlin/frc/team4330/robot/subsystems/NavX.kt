package frc.team4330.robot.subsystems

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.I2C

class NavX : SubsystemBase() {
    val gyro: AHRS

    init {
        gyro = AHRS(I2C.Port.kMXP)
        gyro.reset()
    }

    fun angle(): Double {
        return gyro.angle
    }
}