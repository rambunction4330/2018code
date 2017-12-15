package frc.team4330.robot.drivebase

import kotlin.properties.Delegates

abstract class motors {

    private enum class motorState{
        CONSTANT_SPEED
    }

    abstract var speed: Double

    fun stop(){
        speed = 0.0
    }

}