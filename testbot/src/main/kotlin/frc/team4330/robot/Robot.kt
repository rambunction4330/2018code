package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team4330.robot.CommandGroups.DeliverCube
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.subsystems.Compressor
import frc.team4330.robot.subsystems.Mouth
import frc.team4330.robot.subsystems.robotDrive

class Robot : IterativeRobot() {

    companion object {
        val xbox: Input = Input(RobotMap.DRIVE_JOYSTICK)

        var tank: robotDrive = robotDrive()

        var comp: Compressor = Compressor(RobotMap.PCM_CAN)

        val gyro: AHRS = AHRS(I2C.Port.kMXP)

        val mouth: Mouth = Mouth()

     //   val prototypes: prototypes = prototypes()

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

    override fun autonomousInit() {
        scheduler.removeAll()
        val test: DeliverCube = DeliverCube()
        test.start()
        

    }

    override fun teleopInit() {

    }

    override fun testInit() {
    }


    override fun disabledPeriodic() {}

    override fun autonomousPeriodic() {}

    override fun teleopPeriodic() {
        tank.curveDrive(xbox)
//        prototypes.move(xbox)
        val Vel = { a: Double, b: Double -> (a / b) / 12 / 6 }

        SmartDashboard.putNumber("Right Sensor Position", RobotMap.RIGHT_TALON.getSelectedSensorPosition(0).toDouble())
     //   SmartDsashboard.putNumber("Left Sensor Position", RobotMap.LEFT_TALON.getSelectedSensorPosition(0).toDouble())


        SmartDashboard.putNumber("Right Sensor Velocity", Vel(RobotMap.RIGHT_TALON.getSelectedSensorVelocity(0).toDouble(), 10.71))
        SmartDashboard.putNumber("Left Sensor Velocity", Vel(RobotMap.LEFT_TALON.getSelectedSensorVelocity(0).toDouble(), 10.71))

        print(gyro.angle)
        SmartDashboard.putNumber("Gyro", gyro.angle)
        comp.init()
    }

    override fun testPeriodic() {}
}