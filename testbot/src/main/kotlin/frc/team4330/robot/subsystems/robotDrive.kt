package frc.team4330.robot.subsystems

//import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.drivebase.DifferentialDrive

class robotDrive : SubsystemBase() {

    private var reverse: Boolean = false
    private val mDrive: DifferentialDrive
    var shifted: Boolean

    init {
        RobotMap.RIGHT_VICTOR.follow(RobotMap.RIGHT_TALON)
        RobotMap.RIGHT_VICTOR2.follow(RobotMap.RIGHT_TALON)
        RobotMap.LEFT_VICTOR.follow(RobotMap.LEFT_TALON)
        RobotMap.LEFT_VICTOR2.follow(RobotMap.LEFT_TALON)

        mDrive = DifferentialDrive(RobotMap.LEFT_TALON ,RobotMap.RIGHT_TALON )

        shifted = false
    }

    fun curveDrive(xbox: XboxController) {
        mDrive.curvatureDrive(xbox.getY(GenericHID.Hand.kLeft), -xbox.getX(GenericHID.Hand.kRight), xbox.getTriggerAxis(GenericHID.Hand.kRight) != 0.0)

    }

    fun driveForward(speed: Double, rot: Double, isTriggerPressed: Boolean) {
        mDrive.curvatureDrive(speed, rot, isTriggerPressed)
    }

    fun stop() {
        mDrive.curvatureDrive(0.0, 0.0, false)
    }

    fun upShift() {
        if (!shifted) {
            RobotMap.rightShift.set(true)
            RobotMap.leftShift.set(true)
            shifted = true
        }

    }

    fun downShift() {
        if (shifted) {
            RobotMap.rightShift.set(false)
            RobotMap.leftShift.set(false)
            shifted = false
        }
    }
}