package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.HiiragiMinami;
import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateSensitivityFactor extends Command{
	private HiiragiMinami hiina;
	private double aishite;
	public UpdateSensitivityFactor(double distance){
		hiina = new HiiragiMinami(0,1);
		aishite = distance;
	}
    protected void initialize(){
    	SmartDashboard.putDouble("Sensitivity Factor", hiina.souvenir.getVoltage() / aishite);
    }
    protected void execute(){
    }
    protected boolean isFinished(){
    	if(Robot.torisetsu.checkForChange(5)){
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
