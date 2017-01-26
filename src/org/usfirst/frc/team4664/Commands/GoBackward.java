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
    	Robot.driveTrain.arcadeDrive(-.5,-.5);
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    }
    protected void interrupted() {
    }
}
