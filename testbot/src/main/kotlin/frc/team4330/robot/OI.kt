package frc.team4330.robot

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.Button
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team4330.robot.Commands.InIntake


class OI {
    private val x: XboxController = Robot.xboxOne
    private val y: XboxController = XboxController(1)
    private val z: Joystick = Joystick(1)

    val intakeButton: Button = JoystickButton(x, 2)
    val outtakeButton: Button = JoystickButton(x, 3)
    val spitButton: Button = JoystickButton(x, x.getTriggerAxis(GenericHID.Hand.kRight).toInt())

    init {
        intakeButton.whenPressed(InIntake())
    }
}