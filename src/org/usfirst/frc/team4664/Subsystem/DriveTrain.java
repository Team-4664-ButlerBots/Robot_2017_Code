package org.usfirst.frc.team4664.Subsystem;

import org.usfirst.frc.team4664.Commands.JoyDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem{
    final int lsMotor	  = 0;
    final int rsMotor	  = 1;
    Victor rightSide, leftSide;
    RobotDrive driveTrain;
    public DriveTrain(){
    	rightSide = new Victor(rsMotor);
    	rightSide.setSafetyEnabled(false);
    	leftSide  = new Victor(lsMotor);
    	leftSide.setSafetyEnabled(false);
        driveTrain = new RobotDrive(leftSide, rightSide);
    }
    public void arcadeDrive(double speed1, double speed2){
    	driveTrain.arcadeDrive(speed1, speed2);
    }
	public void initDefaultCommand() {
		setDefaultCommand(new JoyDrive());
	}
}
