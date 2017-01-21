package org.usfirst.frc.team4664.robot;
import org.usfirst.frc.team4664.buttons.GoForward;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
public class Robot extends SampleRobot {
	public GyroM sana;
    final int rangeFinderPort = 1;
    public Robot() {
    	sana = new GyroM(0);
    }
    public void operatorControl() {
        while (isEnabled()) {
        	SmartDashboard.putNumber("Gyro Output: ", sana.cantabile.getAngle());
        	sana.updateGyro();
        	Rangefinder getMeOutOfHere = new Rangefinder(0,1);
    		SmartDashboard.putDouble("Distance: ", getMeOutOfHere.getDistance());
			double sensitivityFactorMeasurements = 0;
			double distance = 12;
    		for(int i = 0; i < 1000; i++){
    			sensitivityFactorMeasurements += getMeOutOfHere.getDistance();
    			SmartDashboard.putDouble("Current Sensitivity Factor at " + distance + " inches",  sensitivityFactorMeasurements / (double)(i));
    		}
        	Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles   
        	SmartDashboard.putData("Drive Foward", new GoForward());
        }
    }
    
    void Autonomous(){
    }
    void Test(){
	}
}