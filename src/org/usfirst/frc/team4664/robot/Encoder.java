package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoder {
	AnalogInput ultraSInput;
	public final double sensitivityFactor = 0; 						//Multiplied by the voltage in order to find distance. Typically measured in units of inches/(milli)Volt.
	public AnalogInput createInput(){
		ultraSInput = new AnalogInput(1);
		ultraSInput.setOversampleBits(8);
		ultraSInput.setAverageBits(4);
		return ultraSInput;
	}
	public double getDistance(){
		double voltage = ultraSInput.getVoltage();
		double distance = voltage * sensitivityFactor;
		SmartDashboard.putDouble("Distance: ", distance);
		return distance;
	}
}
