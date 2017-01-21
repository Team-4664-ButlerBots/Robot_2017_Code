package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GoBackward extends Command{
    public GoBackward(){
    	requires(Robot.driveTrain);
    }
    protected void initialize(){
    }
    protected void execute(){
    	Robot.driveTrain.drive(-.2,-.2);
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    	Robot.driveTrain.drive(0,0);
    }
}
