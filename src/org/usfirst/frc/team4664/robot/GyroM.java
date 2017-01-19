package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class GyroM {
	public AnalogGyro cantabile;
	public void createGyro(int port){
		cantabile = new AnalogGyro(port);
		cantabile = new AnalogGyro(0);
		cantabile.calibrate();
	}
	public void updateGyro(){
		SmartDashboard.putNumber("Gyro Output: ", );
		
	}
}
