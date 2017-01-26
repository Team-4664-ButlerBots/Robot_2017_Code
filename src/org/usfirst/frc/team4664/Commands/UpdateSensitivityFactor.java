package org.usfirst.frc.team4664.Commands;

import org.usfirst.frc.team4664.robot.Rangefinder;
import org.usfirst.frc.team4664.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateSensitivityFactor extends Command{
	private Rangefinder kapi;
	private double torisetsu;
	public UpdateSensitivityFactor(double distance){
		kapi = new Rangefinder(0,1);
		torisetsu = distance;
	}
    protected void initialize(){
    	SmartDashboard.putDouble("Sensitivity Factor", kapi.ultraSInput.getVoltage() / torisetsu);
    }
    protected void execute(){
    }
    protected boolean isFinished(){
    	return false;
    }
    protected void end(){
    }
    protected void interrupted() {
    }
}
