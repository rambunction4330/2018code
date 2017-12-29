package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.DoubleSolenoid

class DoubleSolenoid(port1: Int, port2: Int) : SubsystemBase() {
    val solenoid: DoubleSolenoid = DoubleSolenoid(port1, port2)

    fun forward() {
        solenoid.set(DoubleSolenoid.Value.kForward)
    }

    fun reverse() {
        solenoid.set(DoubleSolenoid.Value.kReverse)
    }

    fun off() {
        solenoid.set(DoubleSolenoid.Value.kOff)
    }

}