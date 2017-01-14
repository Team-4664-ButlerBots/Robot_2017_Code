package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rangefinder {
	AnalogInput ultraSInput;
	public final double sensitivityFactor = 0; 						//Multiplied by the voltage in order to find distance. Typically measured in units of inches/(milli)Volt.
	public AnalogInput setDefaults(int port){
		ultraSInput = new AnalogInput(port);
		ultraSInput.setOversampleBits(8);
		ultraSInput.setAverageBits(4);
		return ultraSInput;
	}
	public double getDistance(){
		double voltage = ultraSInput.getVoltage();
		double distance = voltage * sensitivityFactor;
		return distance;
	}
}
