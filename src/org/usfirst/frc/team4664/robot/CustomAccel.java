package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
//import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class CustomAccel {
		
		boolean accelLoop;
		
		double lastTime=-1;
		double velocity=0;
		
		double xError;
		double yError;
		double zError;
		
		double xVel=0;
		double yVel=0;
		double zVel=0;
		
		double xPos=0;
		double yPos=0;
		double zPos=0;
		BuiltInAccelerometer accel;

		/**
		 * Create an easy accelerometer.
		 * <br>WEW<br>
		 */
	public CustomAccel(){
		accel = new BuiltInAccelerometer();
		
		xError = 0;
		yError = 0;
		zError = 0;
		for(int i=0;i<20;i++){
			xError += accel.getX();
			yError += accel.getY();
			zError += accel.getZ();
		}
		xError /= 20;
		yError /= 20;
		zError /= 20;
		
	}
	/**
	 * <br>This function updates the accelerometer.<br>
	 */
	public void updateAccel(){
		if(lastTime==-1){
			lastTime = System.currentTimeMillis();
		}
		double dt = System.currentTimeMillis()-lastTime;
		
		xVel += (accel.getX() - xError) * dt;
		yVel += (accel.getY() - yError) * dt;
		zVel += (accel.getZ() - zError) * dt;
		velocity = (Math.sqrt(Math.pow(xVel,2) + Math.pow(yVel,2) + Math.pow(zVel,2)));
		xPos+=xVel*dt;
		yPos+=yVel*dt;
		zPos+=zVel*dt;
		
		lastTime = System.currentTimeMillis();
	}
	/**
	 * @return the Y Velocity
	 */
	public double getYvel(){
		return yVel;
	}
	/**
	 * @return the X Velocity
	 */
	public double getxVel(){
		return xVel;
	}
	
	/**
	 * @return the Z Velocity
	 */
	public double getzVel(){
		return zVel;
	}
	/**
	 * @return the X Acceleration
	 */
	public double getXaccel(){
		return accel.getX();
	}
	/**
	 * @return the Y Acceleration
	 */
	public double getYaccel(){
		return accel.getY();
	}
	/**
	 * @return the Z Acceleration
	 */
	public double getZaccel(){
		return accel.getZ();
	}
	
	public double getXpos(){
		return xPos;
	}
	
	public double getYpos(){
		return yPos;
	}
	
	public double getZpos(){
		return zPos;
	}
}
