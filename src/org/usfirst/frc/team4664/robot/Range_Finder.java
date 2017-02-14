package org.usfirst.frc.team4664.robot;


import edu.wpi.first.wpilibj.AnalogInput;


public class Range_Finder{
	double sFactor;		// cm / Volt
	public AnalogInput aInput;
	
	public Range_Finder(int port, int senseFactor){ 
		sFactor = senseFactor;
		aInput = new AnalogInput(port);
		aInput.setOversampleBits(8);
		aInput.setAverageBits(4);
	}
	
	
	public Range_Finder(int port){
		sFactor = 5120 / 10; // cm per Volt; 5120 mm/Volt
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
