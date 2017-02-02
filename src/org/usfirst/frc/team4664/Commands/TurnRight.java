package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRight extends Command{
    public TurnRight(){
    	requires(Robot.driveTrain);
    }
    protected void initialize(){
    }
    protected void execute(){
    	Robot.driveTrain.arcadeDrive(.5,-.5);
    }
    protected boolean isFinished(){
    	double placeholder = Robot.skyLine.aGyro.getAngle();	//initial gyro reading
    	if(Math.abs(placeholder - Robot.skyLine.aGyro.getAngle()) < 90 || Robot.commandList.checkForChange(4)){ //checks for 90 degrees of change
    		return true;
    	} 
    	else{
    		return false;
    	}
    }
    protected void end(){
    }
    protected void interrupted() {
    }
}
