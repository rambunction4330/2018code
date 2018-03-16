package frc.team4330.robot.CommandGroups

import edu.wpi.first.wpilibj.command.CommandGroup
import frc.team4330.robot.Commands.Auto.Forward
import frc.team4330.robot.Pathfinder.Center_left
import frc.team4330.robot.Pathfinder.Center_right
import frc.team4330.robot.Pathfinder.Left
import frc.team4330.robot.Pathfinder.Right
import frc.team4330.robot.Robot
import openrio.powerup.MatchData

class AutoCommand : CommandGroup {
    constructor() {
        when (Robot.dashManager.getStart()) {
            0 -> {
                 // removes all commands from scheduler
                addSequential(Forward()) // adds drive straight
                print("test0")
            }
            1 -> {
                addSequential(Left())
            }
            2 -> when (Robot.automan.side) {
                MatchData.OwnedSide.LEFT -> {
                    addSequential(Center_left())
                    print("testcenterleft")
                }
                MatchData.OwnedSide.RIGHT -> {
                    addSequential(Center_right())
                    print("testcenterright")
                }
                else -> {
                    addSequential(Forward())
                }
            }
            3 -> {
                addSequential(Right())
                print("test3")
            }

            else -> {
                addSequential(Forward())
                print("testelse")
            }

        }
    }
}