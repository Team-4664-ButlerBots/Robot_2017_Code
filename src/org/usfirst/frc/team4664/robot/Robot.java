package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends SampleRobot {
	//Drive System Init
	Talon lDrive = new Talon(0), rDrive = new Talon(1);
	RobotDrive driveSystem = new RobotDrive(lDrive, rDrive);
	AnalogGyro gyro = new AnalogGyro(0);
	AnalogInput rangeFinder = new AnalogInput(1);
	
	UsbCamera camera;
	CvSink cvSink;
	CvSource cvSource;
	Pipeline tPipe;
	Mat source;
	Mat somethingidk;
	ArrayList<MatOfPoint> test;	
	public static final double OFFSET_TO_EDGE = 0;//To the Edge of the Robot
	public static final double CAMERA_PIXEL_WIDTH = 640;
	public static final double WIDTH_BETWEEN_TARGET = 8.5;
	
	double distanceFromTarget;
	double lengthError;
	
	int pixelsBetweenContours;
	int[][] contourArray;
	
	static Pipeline tracker;
	
	private static final double rangeFinderSensitivity = 1;
	Thread visionThread;
	
	//Joystick Declaration
	Joystick gamepad = new Joystick(0);
	Joystick stick   = new Joystick(1);
	
	//RobotStuff
	public Robot() {
		driveSystem.setExpiration(0.1);
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		rangeFinder.setOversampleBits(8);
		rangeFinder.setAverageBits(4);
    	source = new Mat();												//source image
    	somethingidk = new Mat();										//unused image
    	test = new ArrayList<MatOfPoint>();								//array of shapes(i.e. contours/blobs/rectangles)
    	tPipe = new Pipeline();											//CV pipeline(camera processor)
	}

	public void robotInit() {
		gyro.calibrate();    
        visionThread = new Thread(() -> {    	
            cvSink = CameraServer.getInstance().getVideo();						//video
            cvSource = CameraServer.getInstance().putVideo("Theory", 320, 240);	//viewer
            while(!Thread.interrupted()){
            	cvSink.grabFrame(source);										//set source image to Video feed frame
            	tPipe.process(source);											//process source image
            	for(int i = 0; i < tPipe.filterContoursOutput().size(); i++){
                	Imgproc.drawContours(source, tPipe.filterContoursOutput(), i, new Scalar(0,255,255));
            	}
            	cvSource.putFrame(source);										//view processed image
            	test = tPipe.filterContoursOutput();
            	contourArray = obtainValues(test);
            }
        });
        visionThread.setDaemon(true);
        visionThread.start();
	}

	public void autonomous() {
	}
	
	public void operatorControl() {
		driveSystem.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			driveSystem.tankDrive(gamepad.getY(), gamepad.getRawAxis(3));
			tracker = new Pipeline();
			if(getActiveButtons() == 1){
				navigateTowardsContour();
			}
			Timer.delay(0.005);
		}
	}
	
	public void test() {
	}
	
	public int[][] obtainValues(ArrayList<MatOfPoint> m){
		int[][] array = new int[m.size()][7];
		for(MatOfPoint i : m){
			int j = 0;
			Rect rectangle = Imgproc.boundingRect(i);
			int rectWidth = rectangle.width;
			int rectHeight = rectangle.height;
			int rectX = (int) (.5 * rectangle.width + rectangle.x);
			int rectY = (int) (.5 * rectangle.height + rectangle.y);
			int xDistanceFromCenter = (int) (CAMERA_PIXEL_WIDTH - rectX);
			int yDistanceFromCenter = 360 - rectY;
			SmartDashboard.putNumber("Contour " + j +" X Value", rectX);
			SmartDashboard.putNumber("Contour " + j +" Y Value", rectY);
			SmartDashboard.putNumber("Contour " + j +" X Offset", xDistanceFromCenter);
			SmartDashboard.putNumber("Contour " + j +" Y Offset", yDistanceFromCenter);
			SmartDashboard.putNumber("Contour " + j + " Width", rectWidth);
			SmartDashboard.putNumber("Contour " + j + " Height", rectHeight);
			array[j][0] = rectX;
			array[j][1] = rectY;
			array[j][2] = rectWidth;
			array[j][3] = rectHeight;
			array[j][4] = xDistanceFromCenter;
			array[j][5] = yDistanceFromCenter;
			array[j++][6] = Math.abs(array[j-1][0] - array[j][0]);	//pixels between contours
		}
		return array;
	}
	public double distanceFromTarget(){
		return rangeFinder.getVoltage() * 5120; 
	}
	public double getAngle(){
		// 8.5in is for the distance from center to center from goal, then divide by lengthBetweenCenters in pixels to get proportion
		double constant = WIDTH_BETWEEN_TARGET / contourArray[1][6];
		double angleToGoal = 0;
			// this calculates the distance from the center of goal to center of webcam 
			double distanceFromCenterPixels= (contourArray[0][4] + contourArray[1][4]) / 2;
			// Converts pixels to inches using the constant from above.
			double distanceFromCenterInch = distanceFromCenterPixels * constant;
			// math brought to you buy Chris and Jones
			angleToGoal = Math.atan(distanceFromCenterInch / distanceFromTarget());
			angleToGoal = Math.toDegrees(angleToGoal);
			return angleToGoal;
	}
	public void navigateTowardsRed(){
    	ArrayList<MatOfPoint> test1 = new ArrayList<MatOfPoint>();				//view processed image
    	test1 = tPipe.filterContoursOutput();
    	int[][] contourArray = obtainValues(test1);
		while(Math.abs(contourArray[0][4]) <= 4 && getActiveButtons() == 1){
			if(contourArray[0][4] > 4){
				driveSystem.tankDrive(.4, .2);
			}
			else if(contourArray[0][4] < -4){
				driveSystem.tankDrive(.2, .4);
			}
			Timer.delay(0.005);
		}
	}
	public void navigateTowardsContour(){
		while(Math.abs(getAngle()) <= 4){
			if(getAngle() > 4){
				driveSystem.tankDrive(.4, .2);
			}
			else if(getAngle() < -4){
				driveSystem.tankDrive(.2, .4);
			}
			Timer.delay(0.005);
		}
	}
    public int getActiveButtons(){
    	int count = 0;
    	for(int i = 1; i < 14; i++){						//cycles through all possible buttons
    		if(gamepad.getRawButton(i)){					//checks for active button
    			count = i;									//sets count to the highest button value being depressed
    		}
    	}
		return count;										//returns the highest depressed button number
    }
}
