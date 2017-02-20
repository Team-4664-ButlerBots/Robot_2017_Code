package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.math.*;

public class Robot extends SampleRobot implements Constants{
	RobotDrive driveSystem;
	Victor topMotor;
	Joystick stick;
	Joystick gamepad;
	AnalogGyro gyro;
	Range_Finder ultraSonic;
	public Robot() {
		driveSystem = new RobotDrive(lsMotor, rsMotor);
		driveSystem.setExpiration(0.1);
		topMotor =new Victor(2);
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
		gyro.calibrate();
		switch(1){
		case 0:
		auto0();
			break;
		case 1:
		auto1();
			break;
			
		}
	}
	

	@Override
	public void operatorControl() {
		driveSystem.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) 
		{
			if(gamepad.getRawButton(9)){
				driveSystem.tankDrive(0.0, 0.0);
				System.out.println("Emergency disabled for 5 seconds");
				Timer.delay(5);
			}
			//the deadband function receives the inputs gamepad axis and deadband constant
			//it takes these and makes sure no input is given when under the deadband constant.
			driveSystem.tankDrive(deadBand(gamepad.getRawAxis(3),driveDb)*maxSpeed,deadBand(gamepad.getY(),driveDb)*maxSpeed );
			
			
			
			topMotor.set(deadBand(stick.getY(),armDb));
			Dashboard();
			Timer.delay(0.03);
		}
	}
	

	@Override
	public void test() {
	}


	public void auto0(){
		//Not working.
		while(isEnabled()){
			Dashboard();
			driveSystem.arcadeDrive(-0.55, -gyro.getAngle()*0.1);
			Timer.delay(0.05);

			}
		}
	
	public void auto1(){
		while(isEnabled()){
			Dashboard();
			driveSystem.arcadeDrive(-0.5, 10);
			Timer.delay(0.05);

			}
	}
	
	
	public void Dashboard(){
		//ultrasonic Display
		SmartDashboard.putNumber("UltraSonic Distance Cm : ", ultraSonic.getDistance());
		SmartDashboard.putNumber("UltraSonic Voltage : ", ultraSonic.getVolt());
		SmartDashboard.putNumber("UltraSonic distance feet", ultraSonic.getDistance()/30.48);
		//Gyro display
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		//Deadband Display
		SmartDashboard.putNumber("deadBand Left : ", deadBand(gamepad.getY(),driveDb));
		SmartDashboard.putNumber("raw value Left ", gamepad.getY());
		SmartDashboard.putNumber("deadBand Right : ", deadBand(gamepad.getRawAxis(3),driveDb));
		SmartDashboard.putNumber("raw value Right ", gamepad.getRawAxis(3));
		
	}
	
	double deadBand(double AxisInput,double deadband){
		AxisInput=Limit(AxisInput);
		if(Math.abs(AxisInput)<=deadband)
			return 0.0;
		if(AxisInput>deadband)
			return (AxisInput - deadband) / (1.0 - deadband);
		// else
			return (AxisInput + deadband) / (1.0 - deadband);
		
	}
	
	double Limit(double value) {
		if(value > 1.0)  return 1.0;
		if(value < -1.0) return -1.0;
						 return value;
	}
	
	
	double counter=0;
	double firstError=0;
	double lastError=0;
	double currentError;
	double dErr;
	void LineGyro(double CurrentAngle,double TargetAngle){
		currentError=TargetAngle-CurrentAngle;
 
		
		
		Timer.delay(0.05);
	}
}

