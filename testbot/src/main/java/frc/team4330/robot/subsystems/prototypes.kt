package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class prototypes : SubsystemBase() {


    fun move(xbox: Input) {
        if (xbox.rightBumper) {
            RobotMap.CLIMB_LEFT.set(Constants.CLIMB_SPEED)
        } else if (xbox.leftBumper) {
            RobotMap.CLIMB_LEFT.set(-1.0)
        } else if (xbox.aButton) {
            RobotMap.SPOOL1.set(Constants.SPOOL_SPEED)
            RobotMap.SPOOL2.set(Constants.SPOOL_SPEED)
        } else if (xbox.bButton) {
            RobotMap.SPOOL1.set(-Constants.SPOOL_SPEED)
            RobotMap.SPOOL2.set(-Constants.SPOOL_SPEED)
        }
    }

}