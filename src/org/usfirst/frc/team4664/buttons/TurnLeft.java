package org.usfirst.frc.team4664.buttons;

import org.usfirst.frc.team4664.robot.GyroM;

import edu.wpi.first.wpilibj.RobotDrive;

public class TurnLeft {
    RobotDrive polarExpress;
    GyroM gyro = new GyroM(0);
    protected void initialize(){
    }
    protected void execute(){
		polarExpress.arcadeDrive(-.2,.2);
    }
    protected boolean isFinished(){
    	double placeholder = gyro.getAngle();
    	return Math.abs(placeholder - gyro.getAngle()) < 90;
    }
    protected void end(){
    	polarExpress.arcadeDrive(0,0);
    }
}
