package frc.team4330.robot.IO

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.buttons.InternalButton

class FakeButtonClass(port: Int, hand: GenericHID.Hand) : InternalButton() {

    val xbox: XboxController
    val side: GenericHID.Hand

    init {
        xbox = XboxController(port)
        side = hand
    }

    override fun get(): Boolean {
        return xbox.getTriggerAxis(side) != 0.0
    }
}