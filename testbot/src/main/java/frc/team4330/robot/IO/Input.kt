package frc.team4330.robot.IO

import edu.wpi.first.wpilibj.GenericHID.Hand
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


}