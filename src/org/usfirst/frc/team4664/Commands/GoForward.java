package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GoForward extends Command{
    double tenshi;
	public GoForward(){
    	requires(Robot.driveTrain);
    }
    protected void initialize(){
    	tenshi = Robot.skyLine.getAngle();						//initial angle reading
    }
    protected void execute(){
    	if(Robot.skyLine.getAngle() - tenshi > 1){				//corrective movements, if counterclockwise from initial value, turn slightly right
        	Robot.driveTrain.arcadeDrive(.5, .4);
    	}
    	else if(Robot.skyLine.getAngle() - tenshi < -1){		//corrective movements, if clockwise from initial value, turn slightly left
        	Robot.driveTrain.arcadeDrive(.4, .5);
    	}
    	else{
    		Robot.driveTrain.arcadeDrive(.5, .5);
    	}
    }
    protected boolean isFinished(){
    	if(Robot.commandList.checkForChange(1)){
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
