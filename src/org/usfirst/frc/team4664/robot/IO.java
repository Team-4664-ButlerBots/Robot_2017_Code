package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.Joystick;

public class IO {		
    final int controller1Port	= 0;
    final int controller2Port  = 1;
    public static Joystick controller1, controller2;
    static int[] buttonList;
    static int[] activeButtonList;
    public IO(){
        controller1 = new Joystick(controller1Port);
        controller2 = new Joystick(controller2Port);
    	buttonList = new int[14];
    	activeButtonList = new int[14];
    }
    public static double getStickX(){
    	return controller1.getX();
    }
    public static double getStickY(){
    	return controller1.getY();
    }
    public static int getActiveButtons(){
    	int count = 0;
    	for(int i = 1; i < 14; i++){						//cycles through all possible buttons
    		if(controller1.getRawButton(i)){				//checks for active button
    			count = i;									//sets count to the highest button value being depressed
    		}
    	}
		return count;										//returns the highest depressed button number
    }
}
