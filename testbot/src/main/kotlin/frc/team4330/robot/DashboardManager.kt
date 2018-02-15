package frc.team4330.robot

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team4330.robot.IO.RobotMap

class DashboardManager {

    fun getMethod() {

    }

    fun start() {
        val Vel = { a: Double, b: Double -> (a / b) / 12 / 6 }

        SmartDashboard.putNumber("Right Sensor Position", RobotMap.RIGHT_TALON.getSelectedSensorPosition(0).toDouble())
        SmartDashboard.putNumber("Left Sensor Position", RobotMap.LEFT_TALON.getSelectedSensorPosition(0).toDouble())

        SmartDashboard.putNumber("Right Sensor Velocity", Vel(RobotMap.RIGHT_TALON.getSelectedSensorVelocity(0).toDouble(), 10.71))
        SmartDashboard.putNumber("Left Sensor Velocity", Vel(RobotMap.LEFT_TALON.getSelectedSensorVelocity(0).toDouble(), 10.71))

        SmartDashboard.putNumber("Gyro", RobotMap.gyro.angle)

        SmartDashboard.putBoolean("Shifted", Robot.tank.shifted)
    }

    fun getStart() {

    }


}