package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class RangeFinder {	
	double sFactor; 									//Sensitivity Factor
	public AnalogInput aInput;
	public RangeFinder(int port, int senseFactor){
		sFactor = senseFactor; 							//Multiplied by the voltage in order to find distance. Typically measured in units of inches/(milli)Volt.
		aInput = new AnalogInput(port);
		aInput.setOversampleBits(8);
		aInput.setAverageBits(4);
	}
	
	public double getDistance(){
		double voltage = aInput.getVoltage();
		double distance = voltage * sFactor;
		return distance;
	}
}
