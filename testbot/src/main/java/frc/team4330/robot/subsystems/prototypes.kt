package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class prototypes : SubsystemBase() {

    fun move(xbox: Input) {
        if (xbox.rightBumper) {
            RobotMap.CLIMB_LEFT.set(Constants.CLIMB_SPEED)
            RobotMap.CLIMB_RIGHT.set(-Constants.CLIMB_SPEED)
        }

        if (xbox.leftBumper) {
            RobotMap.CLIMB_LEFT.set(-Constants.CLIMB_SPEED)
            RobotMap.CLIMB_RIGHT.set(Constants.CLIMB_SPEED)
        }
    }

}