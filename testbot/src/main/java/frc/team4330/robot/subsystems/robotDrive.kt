package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.Input
import frc.team4330.robot.IO.RobotMap


class robotDrive {

    private val rightTal: WPI_TalonSRX
    private val leftTal: WPI_TalonSRX
    private val rightVic: WPI_VictorSPX
    private val rightVic2: WPI_VictorSPX
    private val leftVic: WPI_VictorSPX
    private val leftVic2: WPI_VictorSPX
    private var reverse: Boolean = false
    private val mLeft: SpeedControllerGroup
    private val mRight: SpeedControllerGroup
    private val mDrive: DifferentialDrive

    init {
        rightVic = WPI_VictorSPX(RobotMap.RIGHT_VICTOR_1);
        rightTal = WPI_TalonSRX(RobotMap.RIGHT_TALON);
        rightVic2 = WPI_VictorSPX(RobotMap.RIGHT_VICTOR_2);

        leftVic = WPI_VictorSPX(RobotMap.LEFT_VICTOR_1);
        leftTal = WPI_TalonSRX(RobotMap.LEFT_TALON);
        leftVic2 = WPI_VictorSPX(RobotMap.LEFT_VICTOR_2);


        mLeft = SpeedControllerGroup(leftTal, leftVic, leftVic2);
        mRight = SpeedControllerGroup(rightTal, rightVic, rightVic2);

        mDrive = DifferentialDrive(mLeft, mRight);

    }

    fun curveDrive(xbox: Input) {
       mDrive.curvatureDrive(xbox.joystickLeftYAxis, xbox.joystickLeftXAxis, xbox.joystickLeftPress)
    }

    fun pathgen() {

    }
}