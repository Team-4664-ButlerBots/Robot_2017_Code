package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class HiiragiMinami {
	double sayonara;
	public AnalogInput souvenir;
	public HiiragiMinami(int port, int senseFactor){
		sayonara = senseFactor; 						//Multiplied by the voltage in order to find distance. Typically measured in units of inches/(milli)Volt.
		souvenir = new AnalogInput(port);
		souvenir.setOversampleBits(8);
		souvenir.setAverageBits(4);
	}
	
	public double getDistance(){
		double voltage = souvenir.getVoltage();
		double distance = voltage * sayonara;
		return distance;
	}
}
