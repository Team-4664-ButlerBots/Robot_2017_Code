package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IO {
    final int joy1Port	= 0;
    final int joy2Port  = 1;
    private Joystick joy1, joy2;
    public IO(){
        joy1 = new Joystick(joy1Port);
        joy2 = new Joystick(joy2Port);
    }
    public double getStickX(){
    	return joy1.getX();
    }
    public double getStickY(){
    	return joy1.getY();
    }
    public int[] getActiveButtons(){
    	int[] bloom = new int[10];
    	int[] lily = new int[10];
    	int count = 0;
    	for(int i = 0; i < 10; i++){
    		if(joy2.getRawButton(i)){
    			bloom[i] = 1;
    		}
    	}
    	for(int i = 0; i < 10; i++){
    		if(bloom[i] == 1){
    			lily[count] = i;
    		}
    	}
		return lily;
    }
}
