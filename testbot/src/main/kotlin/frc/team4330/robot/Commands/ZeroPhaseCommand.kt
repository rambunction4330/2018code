package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.RobotMap

class ZeroPhaseCommand : Command() {

    override fun isFinished(): Boolean {
        return !RobotMap.gyro.isCalibrating
    }
}