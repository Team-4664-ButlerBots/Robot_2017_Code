package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IO {
    final int joy1Port	= 0;
    final int joy2Port  = 1;
    public static Joystick joy1, joy2;
    public IO(){
        joy1 = new Joystick(joy1Port);
        joy2 = new Joystick(joy2Port);
    }
    public static double getStickX(){
    	return joy1.getX();
    }
    public static double getStickY(){
    	return joy1.getY();
    }
    public int[] getActiveButtons(){
    	int[] bloom = new int[11];
    	int[] lily = new int[11];
    	int count = 1;
    	for(int i = 1; i < 11; i++){
    		if(joy2.getRawButton(i)){
    			bloom[i] = 1;
    		}
    	}
    	for(int i = 1; i < 11; i++){
    		if(bloom[i] == 1){
    			lily[count] = i;
    		}
    	}
		return lily;
    }
}
