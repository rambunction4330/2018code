package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class prototypes : SubsystemBase() {

    companion object

    fun move(xbox: Input) {
        if (xbox.rightBumper) {
            RobotMap.CLIMB_LEFT.set(Constants.CLIMB_SPEED)
            RobotMap.CLIMB_RIGHT.set(-Constants.CLIMB_SPEED)
        } else if (xbox.leftBumper) {
            RobotMap.CLIMB_LEFT.set(-Constants.CLIMB_SPEED)
            RobotMap.CLIMB_RIGHT.set(Constants.CLIMB_SPEED)
        } else if (xbox.aButton) {
            RobotMap.SPOOL1.set(Constants.SPOOL_SPEED)
            RobotMap.SPOOL2.set(Constants.SPOOL_SPEED)
        } else if (xbox.bButton) {
            RobotMap.SPOOL1.set(-Constants.SPOOL_SPEED)
            RobotMap.SPOOL2.set(-Constants.SPOOL_SPEED)
        } else
            RobotMap.SPOOL1.disable()
        RobotMap.SPOOL2.disable()
        RobotMap.CLIMB_LEFT.disable()
        RobotMap.CLIMB_RIGHT.disable()
    }

}