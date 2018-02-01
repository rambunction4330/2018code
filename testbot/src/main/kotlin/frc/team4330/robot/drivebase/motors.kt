package frc.team4330.robot.drivebase

abstract class motors {

    private enum class motorState{
        CONSTANT_SPEED
    }

    abstract var speed: Double

    fun stop(){
        speed = 0.0
    }

}