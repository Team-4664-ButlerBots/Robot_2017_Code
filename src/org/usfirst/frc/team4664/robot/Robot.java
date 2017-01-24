package org.usfirst.frc.team4664.robot;
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
    public GyroM sana;
    
    public Robot() {
    	
    	rightSide = new Victor(rsMotor);
    	leftSide  = new Victor(lsMotor);
    	armSpeed  = new Victor(armSPort);
    	armTorque = new Victor(armTPort);
    	lattice   = new Victor(latPort);
    	winch     = new Victor(winchPort);
        driveTrain = new RobotDrive(leftSide, rightSide);
        joy1 = new Joystick(joy1Port);
        joy2 = new Joystick(joy2Port);
        sana = new GyroM(0);
        
    	}
	
  
    
    public void operatorControl() {
        while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", sana.cantabile.getAngle());
        	sana.updateGyro();

        		Timer.delay(.2);
        	}
        	Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles   
        }
    
    
    void Autonomous(){
    	driveTrain.setSafetyEnabled(false);
    	RobotDrive.setSafetyEnabled(false);
    	while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", sana.cantabile.getAngle());
        	sana.updateGyro();
        	GyroLineAuto();
        	
        	
       }
    }
    void Test(){
	}
    void GyroLineAuto(){
    	if(sana.cantabile.getAngle()>2){
    		System.out.println("angle greater than 2");
    		driveTrain.arcadeDrive(1, (sana.cantabile.getAngle())*-1);
    	}else{
    		if(sana.cantabile.getAngle()>-2){
    			System.out.println("angle less than 2");
    			driveTrain.arcadeDrive(1, sana.cantabile.getAngle());
    		}
    	}
    	Timer.delay(0.01);
    }

}