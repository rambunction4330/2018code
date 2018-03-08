package frc.team4330.robot

import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.Button
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team4330.robot.Commands.*
import frc.team4330.robot.IO.RobotMap


class OI {
    private val x: XboxController = RobotMap.XBOX_OI
    private val y: XboxController = RobotMap.XBOX_INTAKE

    //Drive Controls
    val shifter: Button = JoystickButton(x, 5)
    val shifterDown: Button = JoystickButton(x, 6)
    val openCloseMouthButton: Button = JoystickButton(x, 1)//a

    //Controller 2
    val succButton: Button = JoystickButton(y, 1)//a
    val spitButton: Button = JoystickButton(y, 3) //x
    val mouthmoveButton: Button = JoystickButton(y, 4) //y
    val stopLipsButton: Button = JoystickButton(y, 2) //b
//    val spoolReel: Button = JoystickButton(y, 7) // back button
//    val spoolReelReverse: Button = JoystickButton(y, 8) //start button
//    val climberButton: Button = JoystickButton(y, 5) //left bumper
//    val climberReverse: Button = JoystickButton(y, 6) //right bumper
    //val nidecRight: Button = JoystickButton(x, )
    //val nidecLeft: Button = JoystickButton(x, )


    constructor() {
        //Drive Controls
        shifter.whenPressed(shiftup())
        shifterDown.whenPressed(shiftdown())
        openCloseMouthButton.whileHeld(CloseOpenMouth())

        //Controller 2
        spitButton.whileHeld(LipsSpit())
        succButton.whileHeld(LipsSucc())
        mouthmoveButton.whenPressed(MoveMouth())
        stopLipsButton.cancelWhenPressed(LipsSpit())
        stopLipsButton.cancelWhenPressed(LipsSucc())
    }
}