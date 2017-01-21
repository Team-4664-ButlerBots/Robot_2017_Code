package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rangefinder {
	AnalogInput ultraSInput;
	double sensitivityFactor;
	public Rangefinder(int port, int senseFactor){
		double sensitivityFactor = senseFactor; 						//Multiplied by the voltage in order to find distance. Typically measured in units of inches/(milli)Volt.
		ultraSInput = new AnalogInput(port);
		ultraSInput.setOversampleBits(8);
		ultraSInput.setAverageBits(4);
	}
	
	public double getDistance(){
		double voltage = ultraSInput.getVoltage();
		double distance = voltage * sensitivityFactor;
		return distance;
	}
}
