package org.usfirst.frc.team4664.Subsystem;

import org.usfirst.frc.team4664.Commands.JoyDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem{
    final int lVicPort	  = 0;
    final int rVicPort	  = 1;
    Victor lVic, rVic;
    RobotDrive driveTrain;
    public DriveTrain(){
    	rVic = new Victor(rVicPort);
    	rVic.setSafetyEnabled(false);
    	lVic  = new Victor(lVicPort);
    	lVic.setSafetyEnabled(false);
        driveTrain = new RobotDrive(lVic, rVic);
    }
    public void arcadeDrive(double speed1, double speed2){
    	driveTrain.arcadeDrive(speed1, speed2);
    }
	public void initDefaultCommand() {
		setDefaultCommand(new JoyDrive());
	}
}
