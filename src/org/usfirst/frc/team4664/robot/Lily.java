package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Lily {
    final int root1Port	= 0;
    final int root2Port  = 1;
    public static Joystick root1, root2;
    static int[] bloom;
    static int[] stem;
    public Lily(){
        root1 = new Joystick(root1Port);
        root2 = new Joystick(root2Port);
    	bloom = new int[14];
    	stem = new int[14];
    }
    public static double getStickX(){
    	return root1.getX();
    }
    public static double getStickY(){
    	return root1.getY();
    }
    public static int[] getActiveButtons(){
    	int count = 1;
    	for(int i = 1; i < 14; i++){
    		if(root1.getRawButton(i)){
    			bloom[i] = 1;
    		}
    	}
    	for(int i = 1; i < 14; i++){
    		if(bloom[i] == 1){
    			stem[count] = i;
    			count++;
    		}
    	}
		return stem;
    }
}
