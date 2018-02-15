package frc.team4330.robot.IO

import  edu.wpi.first.wpilibj.GenericHID.Hand
import edu.wpi.first.wpilibj.XboxController

class Input(port: Int) {
    private val xbox: XboxController = XboxController(port)


    val joystickLeftYAxis: Double
        get() = xbox.getY(Hand.kLeft)

    val joystickRightYAxis: Double
        get() = xbox.getY(Hand.kRight)

    val joystickLeftXAxis: Double
        get() = xbox.getX(Hand.kLeft)

    val joystickRightXAxis: Double
        get() = xbox.getX(Hand.kRight)

    val leftTriggerAxis: Double
        get() = xbox.getTriggerAxis(Hand.kLeft)

    val rightTriggerAxis: Double
        get() = xbox.getTriggerAxis(Hand.kRight)

    val rightBumper: Boolean
        get() = xbox.getBumper(Hand.kRight)

    val leftBumper: Boolean
        get() = xbox.getBumper(Hand.kLeft)

    val aButton: Boolean
        get() = xbox.aButton

    val bButton: Boolean
        get() = xbox.bButton

    val xButton: Boolean
        get() = xbox.xButton

    val xButtonPressed: Boolean
        get() = xbox.xButtonPressed

    val yButton: Boolean
        get() = xbox.yButton

    fun isRightTriggerPressed(): Boolean {
        return rightTriggerAxis != 0.0
    }

    fun isLeftTriggerPressed(): Boolean {
        return leftTriggerAxis != 0.0
    }

    val startButton: Boolean
        get() = xbox.startButton

    val backButton: Boolean
        get() = xbox.backButton
}