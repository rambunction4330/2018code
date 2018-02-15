package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.Constants
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap

class Climber : SubsystemBase() {

    init {
        RobotMap.CLIMB_LEFT.configOpenloopRamp(.5, 10)
        RobotMap.CLIMB_LEFT.configPeakCurrentLimit(40, 10)
        RobotMap.CLIMB_LEFT.configPeakCurrentDuration(3000, 10)
        RobotMap.CLIMB_LEFT.configContinuousCurrentLimit(25, 10)
        RobotMap.SPOOL1.configContinuousCurrentLimit(25,10)
        RobotMap.SPOOL1.configPeakCurrentDuration(1000, 10)
        RobotMap.SPOOL1.configPeakCurrentLimit(35, 10)
        RobotMap.SPOOL1.configOpenloopRamp(.5, 10)
    }

    fun move(xbox: Input) {

        when {
            xbox.rightBumper -> RobotMap.CLIMB_LEFT.set(ControlMode.PercentOutput, Constants.CLIMB_SPEED)
            xbox.leftBumper -> RobotMap.CLIMB_LEFT.set(ControlMode.PercentOutput, -Constants.CLIMB_SPEED)
            xbox.aButton -> {
                RobotMap.SPOOL1.set(ControlMode.PercentOutput, Constants.SPOOL_SPEED)
            }
            xbox.bButton -> {
                RobotMap.SPOOL1.set(ControlMode.PercentOutput, -Constants.SPOOL_SPEED)
            }
            else -> {
                RobotMap.CLIMB_LEFT.set(ControlMode.PercentOutput, 0.0)
                RobotMap.SPOOL1.set(ControlMode.PercentOutput, 0.0)
            }
        }
    }

}