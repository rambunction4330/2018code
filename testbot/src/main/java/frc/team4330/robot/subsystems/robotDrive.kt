package frc.team4330.robot.subsystems

import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.drivebase.Talons
import frc.team4330.robot.drivebase.Victor

class robotDrive : SubsystemBase() {

    private val rightTal: Talons
    private val leftTal: Talons
    private val rightVic: Victor
    private val leftVic: Victor
    private var reverse: Boolean = false

    init {
        rightVic = Victor(RobotMap.RIGHT_VICTOR_PWM)
        rightTal = Talons(RobotMap.RIGHT_TALON)

        leftVic = Victor(RobotMap.LEFT_VICTOR_PWM)
        leftTal = Talons(RobotMap.LEFT_TALON)
    }

    fun autoDrive(left: Double, right: Double) {
        leftTal.speed = left * -1.0
        leftVic.speed = left * -1.0

        rightTal.speed = right
        rightVic.speed = right
    }

    fun tankDrive(xbox: Input) {
        if (reverse) {
            autoDrive(xbox.joystickRightYAxis, xbox.joystickLeftYAxis)
        } else
            autoDrive(xbox.joystickLeftYAxis, xbox.joystickRightYAxis)
    }

}