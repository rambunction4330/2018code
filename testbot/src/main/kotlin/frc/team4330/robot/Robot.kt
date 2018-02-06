package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.Compressor
import frc.team4330.robot.subsystems.prototypes
import frc.team4330.robot.subsystems.robotDrive

class Robot : TimedRobot() {

    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)

        var tank: robotDrive = robotDrive()

        var comp: Compressor = Compressor(RobotMap.PCM_CAN)

        val gyro: AHRS = AHRS(I2C.Port.kMXP)



        val prototypes: prototypes = prototypes()

    }

    private lateinit var scheduler: Scheduler

    override fun robotInit() {
        comp.init()
        CameraServer.getInstance().startAutomaticCapture()

        RobotMap.RIGHT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.LEFT_TALON.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10)
        RobotMap.RIGHT_TALON.setSensorPhase(true)
    }

    override fun disabledInit() {
        comp.stop()
    }

    override fun autonomousInit() {}

    override fun teleopInit() {

    }

    override fun testInit() {
    }


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {}

    override fun teleopPeriodic() {
        tank.curveDrive(xbox)
        prototypes.move(xbox)
    }

    override fun testPeriodic() {}
}