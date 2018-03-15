package frc.team4330.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import frc.team4330.robot.IO.RobotMap

class NidecArm : SubsystemBase() {
    fun back() {
        RobotMap.nidecMotor.set(-.2)
    }

    fun forwards() {
        RobotMap.nidecMotor.set(.2)
    }
}