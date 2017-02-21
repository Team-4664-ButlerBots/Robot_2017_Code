package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot implements Constants{
	RobotDrive driveSystem;
	Victor collectMotor;
	Victor shootMotor;
	Victor hopperMotor;
	Victor climbMotor;
	Joystick stick;
	Joystick gamepad;
	AnalogGyro gyro;
	Range_Finder ultraSonic;
	
	public Robot() {
		//Motors
		driveSystem = new RobotDrive(lsMotor, rsMotor);
		driveSystem.setExpiration(0.1);
		hopperMotor=new Victor(hopperPort);
		collectMotor =new Victor(collectMPort);
		shootMotor=new Victor(shootMPort);
		climbMotor=new Victor(climbMPort);
		//Input
		stick = new Joystick(gamepadPort);
		gamepad = new Joystick(joystickPort);
		
		//Sensors
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
	

	boolean collectorOn=false;
	boolean collectorInv = false;
	@Override
	public void operatorControl() {
		driveSystem.setSafetyEnabled(false);
		hopperMotor.set(1.0);
		while (isOperatorControl() && isEnabled()) 
		{

		
			//the deadband function receives the inputs gamepad axis and deadband constant
			//it takes these and makes sure no input is given when under the deadband constant.
			driveSystem.tankDrive(deadBand(gamepad.getRawAxis(3),driveDb)*maxSpeedDrive,deadBand(gamepad.getY(),driveDb)*maxSpeedDrive );

			
			//COLECTOR CONTROL
			if(stick.getRawButton(collectInB)){
				
				collectorOn=true;
				collectorInv = false;
			}
			if(stick.getRawButton(collectOutB)){
				collectorOn= true;
				collectorInv = true;
			}
			if(stick.getRawButton(collectStopB))
				collectorOn=false;
			
			if(collectorOn){
				if(collectorInv)
					collectMotor.set(1.0);
				else
					collectMotor.set(-1.0);
			}else
				collectMotor.set(0.0);
			
			
			
			//Shoot Control
			if(stick.getTrigger()){
				shootMotor.set(-.65);
			}else{
				shootMotor.set(0);
				
			}
			
			
			//Climb Motor
			climbMotor.set(deadBand(Limit(-stick.getY(),0.0,1.0),climbDb));
			
			
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
		
		SmartDashboard.putNumber("climb power", deadBand(Limit(-stick.getY(),0.0,1.0),climbDb));
		

		
	}
	
	double deadBand(double AxisInput,double deadband){
		AxisInput=Limit(AxisInput,-1.0,1.0);
		if(Math.abs(AxisInput)<=deadband)
			return 0.0;
		if(AxisInput>deadband)
			return (AxisInput - deadband) / (1.0 - deadband);
		// else
			return (AxisInput + deadband) / (1.0 - deadband);
		
	}
	
	double Limit(double value,double min,double max) {
		if(value > max)  return max;
		if(value < min) return min;
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

