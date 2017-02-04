package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import java.math.*;

public class StraightLine {
	double LeftPower;
	double RightPower;
	
	final int lsMotor	  = 0;
	final int rsMotor	  = 1;
	
	 RobotDrive driveTrain;
	 Victor rightSide,leftSide;
	public StraightLine(){
		rightSide = new Victor(0);
		leftSide = new Victor(1);
		driveTrain = new RobotDrive(leftSide, rightSide);
	}
	
	/*
	 * This Class Gets the robot to drive in a straight line based on the gyro values.
	 * Lower# for line power= more power go from 0 to 360.
	 * 
	 */
	public void GyroLineDrive(double InputGyroAngle,double speed,double LinePower){
		LeftPower=speed;
		RightPower=speed;
		
		if(InputGyroAngle>2){//turn left. right motot more power
    		System.out.println("Greater than 2  :"+InputGyroAngle);
    		RightPower=((-InputGyroAngle)/LinePower)+speed;

    	}else{
    		if(InputGyroAngle<-2){
    			System.out.println("angle less than 2   :"+InputGyroAngle);
    		LeftPower=((InputGyroAngle)/LinePower)+speed;
    		
    		}
    	}
    	driveTrain.setLeftRightMotorOutputs(LeftPower*-1, RightPower*-1);
    	Timer.delay(0.01);
    	System.out.println("right power : "+RightPower);
		System.out.println("left power :"+LeftPower);

	}
	
	public void GryoLineArcade(double InputGyroAngle,double speed,boolean Linepower){
		/*add a value to the Input Gyro angle to increase correction then do a if statment
		 * to not add this within 6 degrees. -3 to 3.
		 * 
		 */
		if(Math.abs(InputGyroAngle)>360){
			driveTrain.arcadeDrive(speed, InputGyroAngle/5, false);
		}else if(Math.abs(InputGyroAngle)>180){
			driveTrain.arcadeDrive(speed, InputGyroAngle/13, false);
		}else if(Math.abs(InputGyroAngle)>80){
			driveTrain.arcadeDrive(speed, InputGyroAngle/20, false);
		}else{
			driveTrain.arcadeDrive(speed, InputGyroAngle/25, false);
		}
	}
	
	
	public double GetLeftPower(){
		return LeftPower;
	}
	
	public double GetRightPower(){
		return RightPower;
	}

}
