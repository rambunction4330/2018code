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
    //val shifterDown: Button = JoystickButton(x, 6)
    val openCloseMouthButton: Button = JoystickButton(x, 1)//a
    val openCloseMouth2Button: Button = JoystickButton(x, 2) //b
    val openCLoseMouth3: Button = JoystickButton(x, 6)
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
        shifter.toggleWhenPressed(shiftup())
        //  shifterDown.whenPressed(shiftdown())
        openCLoseMouth3.toggleWhenPressed(CloseOpenMouth())
        openCloseMouthButton.toggleWhenPressed(CloseOpenMouth())
        openCloseMouth2Button.toggleWhenPressed(CloseOpenMouth2())

        //Controller 2
        spitButton.whenPressed(LipsSpit())
        succButton.whenPressed(LipsSucc())
        mouthmoveButton.toggleWhenPressed(MoveMouth())
        stopLipsButton.cancelWhenPressed(LipsSucc())
        stopLipsButton.cancelWhenPressed(LipsSpit())
    }
}