package org.usfirst.frc.team4664.robot;
<<<<<<< HEAD

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	//Drive System Init
	RobotDrive driveSystem = new RobotDrive(0, 1);
	
	//Joystick Declaration
	Joystick gamepad = new Joystick(0);
	Joystick stick   = new Joystick(1);
	
	//RobotStuff
	public Robot() {
		driveSystem.setExpiration(0.1);
	}

	//Init Stuff
	public void robotInit() {
	}

	//Autonomous, activated in Autonomous mode
	public void autonomous() {
	}

	//Operator mode, activated in TeleOperated mode
	public void operatorControl() {
		//TODO Test actual code
		driveSystem.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			driveSystem.arcadeDrive(gamepad);
			Timer.delay(0.005);
		}
	}

	//Test mode, activated in Test mode
	public void test() {
	}
}
=======
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
public class Robot extends SampleRobot {
	//Systems
    RobotDrive driveTrain;
    Joystick joy1, joy2;
    //Motors
    Victor rightSide, leftSide;//Drive train motors
    Victor lattice, winch;//The Scissor lift & winch respectively
    Victor armSpeed, armTorque;//armSpeed spins the in take wheels; armTorque moves input in out
    //Ports
    final int lsMotor	  = 0;
    final int rsMotor	  = 1;
    final int armTPort    = 2;
    final int armSPort	  = 3;
    final int latPort     = 4;
    final int winchPort   = 5;
    //joystick 2 buttons
    final int armSpeedB    = 6;
    final int latticeUpB   = 3;
    final int latticeDownB = 2;
    final int winchOutB    = 4;
    final int winchInB     = 5;
    //speed variables
    final double armSpeedVal   = 0.25;
    final double winchOut      = 1.0;
    final double winchIn       = -.7;
    final double latticeUp     = 0.8;
    final double latticeDown   = -.5;
    //dead band variables
    final double driveXDb    = 0.3;
    final double driveYDb    = 0.3;
    final double armTorqueDb = 0.2;
    //Laptop ports
    final int joy1Port	= 0;
    final int joy2Port  = 1;
    public GyroM gyro;
    //motor speeds
    double leftSpeed=1;
  	double rightSpeed=1;
  	StraightLine GyroStraightLine;
    
    public Robot() {
    	
    	armSpeed  = new Victor(armSPort);
    	armTorque = new Victor(armTPort);
    	lattice   = new Victor(latPort);
    	winch     = new Victor(winchPort);
        joy1 = new Joystick(joy1Port);
        joy2 = new Joystick(joy2Port);
        gyro = new GyroM(0);
        
        GyroStraightLine=new StraightLine();
        
    	}
	
  
    
    public void operatorControl() {
        while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", gyro.cantabile.getAngle());
        	gyro.updateGyro();

        		Timer.delay(.2);
        	}
        	Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles   
        }
    
    
    public void autonomous(){
  	gyro.cantabile.calibrate();
    	while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", gyro.cantabile.getAngle());
        	gyro.updateGyro();
        	//GyroStraightLine.GyroLineDrive(gyro.cantabile.getAngle(),0.1,70.0);
        	GyroStraightLine.GryoLineArcade(gyro.cantabile.getAngle(), 0.25, false);
        	System.out.println(gyro.cantabile.getAngle());
        	
    	}
    }
    void Test(){
	}
    
    //gyro works clockwise.
    void GyroLineAuto(){
    	
    	if(gyro.cantabile.getAngle()>2){//turn left. right motor more power
    		System.out.println("Greater than 2  :"+gyro.cantabile.getAngle());
    		//driveTrain.arcadeDrive(1, (gyro.cantabile.getAngle())*-1);
    		leftSpeed=gyro.cantabile.getAngle()/100;
    	}else{
    		if(gyro.cantabile.getAngle()<-2){
    			System.out.println("angle less than 2   :"+gyro.cantabile.getAngle());
    			//driveTrain.arcadeDrive(1, gyro.cantabile.getAngle());
    		rightSpeed=gyro.cantabile.getAngle()/100;
    		}
    	}
    	driveTrain.setLeftRightMotorOutputs(leftSpeed, rightSpeed*-1);
    	Timer.delay(0.01);
    }

}
>>>>>>> 07611698ce8aba4991c166d7849d8284bbda5c92
