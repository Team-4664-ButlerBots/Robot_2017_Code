package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot implements Constants{
	RobotDrive driveSystem;
	Joystick stick;
	Joystick gamepad;
	AnalogGyro gyro;
	Range_Finder ultraSonic;
	public Robot() {
		driveSystem = new RobotDrive(lsMotor, rsMotor);
		driveSystem.setExpiration(0.1);
		stick = new Joystick(gamepadPort);
		gamepad = new Joystick(joystickPort);
		gyro = new AnalogGyro(gyroSense);
		gyro.calibrate();
		ultraSonic = new Range_Finder(rangeFinder);
	}

	@Override
	public void robotInit() {
	}

	@Override
	public void autonomous() {
		switch(0){
		case 0:
		auto0();
			break;
			
		}
	}
	

	@Override
	public void operatorControl() {
		driveSystem.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			if(gamepad.getRawButton(9)){
				driveSystem.tankDrive(0.0, 0.0);
				System.out.println("Emergency disabled for 5 seconds");
				Timer.delay(5);
			}
			//driveSystem.tankDrive(speedStepL.SpeedStepper(gamepadr.getY()), speedStepR.SpeedStepper(gamepad.getTwist()));
			driveSystem.tankDrive(gamepad.getY()*0.9, gamepad.getRawAxis(3)*0.9);
		}
	}
	

	@Override
	public void test() {
	}


	public void auto0(){
		while(isEnabled()){
			if(ultraSonic.getDistance()>30){
		 	driveSystem.arcadeDrive(0.5, gyro.getAngle()/18);
			SmartDashboard.putNumber("UltraSonic Distance : ", ultraSonic.getDistance());
			//Limit reached Driving blind for 1 seconds
		}else{
			Timer.delay(1);
			driveSystem.arcadeDrive(0, 0);
			}
		}
	}	
}

