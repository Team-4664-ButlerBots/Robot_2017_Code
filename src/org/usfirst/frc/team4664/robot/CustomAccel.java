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
		
		double xVel;
		double yVel;
		double zVel;
		BuiltInAccelerometer accel;

	public CustomAccel(){
		accel = new BuiltInAccelerometer();
		
		xError=0;
		yError=0;
		zError=0;
		for(int i=0;i<20;i++){
			xError+=accel.getX();
			yError+=accel.getY();
			zError+=accel.getZ();
		}
		xError/=20;
		yError/=20;
		zError/=20;
		
	}
	
	public void updateAccel(){
		if(lastTime==-1){
			lastTime = System.currentTimeMillis();
		}
		double dt = System.currentTimeMillis()-lastTime;
		
		xVel+=(accel.getX()-xError)*dt;
		yVel+=(accel.getY()-yError)*dt;
		zVel+=(accel.getZ()-zError)*dt;
		velocity=(Math.sqrt(Math.pow(xVel,2) + Math.pow(yVel,2) + Math.pow(zVel,2)));
		lastTime = System.currentTimeMillis();
	}
	public double getYvel(){
		return yVel;
	}
	
	public double getxVel(){
		return xVel;
	}
	
	public double getzVel(){
		return zVel;
	}
	
	public double getXaccel(){
		return accel.getX();
	}
	
	public double getYaccel(){
		return accel.getY();
	}
	
	public double getZaccel(){
		return accel.getZ();
	}
	
	

}
