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
    	double placeholder = Robot.sana.cantabile.getAngle();
    	if(Math.abs(placeholder - Robot.sana.cantabile.getAngle()) < 90 || Robot.torisetsu.checkForChange(4)){
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
