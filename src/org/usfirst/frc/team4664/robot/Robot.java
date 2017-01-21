package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.buttons.GoForward;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
public class Robot extends SampleRobot {
	public GyroM sana;
    public Robot() {
    	sana = new GyroM(0);
    	}
    public void operatorControl() {
        while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", sana.cantabile.getAngle());
        	sana.updateGyro();
        	Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles   
        	SmartDashboard.putData("Drive Foward", new GoForward());
        }
    }
    
    void Autonomous(){
    }
    void Test(){
	}
}