package org.usfirst.frc.team4664.robot;


import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroM {
	public AnalogGyro cantabile;
	public int cycleCount = 0;
	public GyroM(int port){
		cantabile = new AnalogGyro(port);
		cantabile.calibrate();
	}
	public void updateGyro(){
		SmartDashboard.putDouble("Gyro Value", this.getAngle());
	}
	public double getAngle(){
		double placeholder = (int)(cantabile.getAngle() * 100 + .5)/100.0;
		return placeholder;
	}	
}
