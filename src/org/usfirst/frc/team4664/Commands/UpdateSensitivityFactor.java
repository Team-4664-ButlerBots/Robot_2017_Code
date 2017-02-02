package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateSensitivityFactor extends Command{
	private double cDistance;
	public UpdateSensitivityFactor(double distance){
		cDistance = distance;
	}
    protected void initialize(){
    	SmartDashboard.putNumber("Sensitivity Factor", Robot.headset.aInput.getVoltage() / cDistance);
    }
    protected void execute(){
    }
    protected boolean isFinished(){
    	if(Robot.commandList.checkForChange(5)){
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
