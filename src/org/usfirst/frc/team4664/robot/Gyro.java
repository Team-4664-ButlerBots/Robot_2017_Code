package org.usfirst.frc.team4664.robot;


import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro{		//sana and her songs relate to the Gyro
	public AnalogGyro aGyro;
	public Gyro(int port){
		aGyro = new AnalogGyro(port);
		aGyro.calibrate();
	}
	public void updateGyro(){
		SmartDashboard.putNumber("Gyro Value", this.getAngle());
	}
	public double getAngle(){
		double placeholder = (int)(aGyro.getAngle() * 100 + .5)/100.0;
		return placeholder;
	}
}
