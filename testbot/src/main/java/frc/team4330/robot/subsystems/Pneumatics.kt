package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.RobotMap

class Pneumatics {
    val p1: DoubleSolenoid
    val p2: DoubleSolenoid

    init {
        p1 = DoubleSolenoid(RobotMap.PISTON_ONE_0, RobotMap.PISTON_ONE_1)
        p2 = DoubleSolenoid(RobotMap.PISTON_TWO_0, RobotMap.PISTON_TWO_1)
    }


}