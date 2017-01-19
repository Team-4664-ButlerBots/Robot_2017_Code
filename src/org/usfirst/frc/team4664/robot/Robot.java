package org.usfirst.frc.team4664.robot;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
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
        	for(int i = 0; i < 10; i++){
        		sana.printRestingUpdateCycle(i);
        		Timer.delay(.2);
        	}
        	Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles   
        }
    }
    
    void Autonomous(){
    }
    
    void Test(){
	}

}