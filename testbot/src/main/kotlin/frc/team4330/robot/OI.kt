package frc.team4330.robot

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.Button
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team4330.robot.Commands.InIntake
import frc.team4330.robot.IO.RobotMap


class OI {
    private val x: XboxController = RobotMap.XBOX_OI

    val intakeButton: Button = JoystickButton(x, 2) //x button
    val outtakeButton: Button = JoystickButton(x, 3) //y button
    val spitButton: Button = JoystickButton(x, x.getTriggerAxis(GenericHID.Hand.kRight).toInt())
    val climberButton: Button = JoystickButton(x, 13) //left bumper?
    val climberReverse: Button = JoystickButton(x, 14) //right bumper?
    val spoolReel: Button = JoystickButton(x, 6) // back button
    val spoolReelReverse: Button = JoystickButton(x, 7) //start button




    init {
        intakeButton.whenPressed(InIntake())
    }
}