package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroM {
	public AnalogGyro cantabile;
	public GyroM(int port){
		cantabile = new AnalogGyro(port);
		cantabile.calibrate();
	}
	public void updateGyro(){
		double sana = ((int)(cantabile.getAngle() * 100 + .5) / 100.0);
		SmartDashboard.putDouble("Gyro Value", sana);
	}
	public void printRestingUpdateCycle(int cycleN){
		double[] sana = new double[10];
		for(int i = 0; i < 10; i++) {
			sana[i] = cantabile.getRate();
			Timer.delay(.2);
		}
		double skyline = 0;
		for(int i = 0; i < sana.length; i++) {
			skyline += sana[i];
		}
		skyline /= 10.0;
		SmartDashboard.putDouble("Gyro Rate " + cycleN, skyline);
	}
}
