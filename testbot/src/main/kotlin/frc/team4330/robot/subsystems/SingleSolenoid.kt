package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.Solenoid

class SingleSolenoid(port1: Int) : SubsystemBase() {
    val solenoid: Solenoid = Solenoid(port1)

    fun forward() {
        solenoid.set(true)
    }

    fun reverse() {
        solenoid.set(false)
    }
}