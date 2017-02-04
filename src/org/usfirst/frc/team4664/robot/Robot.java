package org.usfirst.frc.team4664.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	//Drive System Init
	Victor lDrive = new Victor(0), rDrive = new Victor(1);
	RobotDrive driveSystem = new RobotDrive(0, 1);
	AnalogGyro gyro = new AnalogGyro(0);
	AnalogInput rangeFinder = new AnalogInput(1);
	
	UsbCamera camera;
	CvSink cvSink;
	CvSource cvSource;
	Pipeline tPipe;
	public final int centerX = 320;
	public final int centerY = 240;
	public static VideoCapture videoCapture;
	
	private static final double rangeFinderSensitivity = 1;
	
	//Joystick Declaration
	Joystick gamepad = new Joystick(0);
	Joystick stick   = new Joystick(1);
	
	//RobotStuff
	public Robot() {
		driveSystem.setExpiration(0.1);
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(480, 360);
            
        cvSink = CameraServer.getInstance().getVideo();						//video
        cvSource = CameraServer.getInstance().putVideo("Theory", 640, 480);	//viewer
	}

	//Init Stuff
	public void robotInit() {
		gyro.calibrate();
	}

	//Autonomous, activated in Autonomous mode
	public void autonomous() {
	}

	//Operator mode, activated in TeleOperated mode
	public void operatorControl() {
		//TODO Test actual code
		driveSystem.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			driveSystem.tankDrive(gamepad.getX(), gamepad.getY());
			
			
        	Mat source = new Mat();											//source image
        	Mat somethingidk = new Mat();									//unused image
        	ArrayList<MatOfPoint> test = new ArrayList<MatOfPoint>();		//array of shapes(i.e. contours/blobs/rectangles)
        	tPipe = new Pipeline();											//CV pipeline(camera processor)

        	cvSink.grabFrame(source);										//set source image to Video feed frame
        	tPipe.process(source);											//process source image
        	Imgproc.drawContours(source, tPipe.filterContoursOutput(), 10, new Scalar(0,255,255));
        	cvSource.putFrame(source);										//view processed image
        	obtainValues(test);
			Timer.delay(0.005);
		}
	}

	//Test mode, activated in Test mode
	public void test() {
	}
	public void obtainValues(ArrayList<MatOfPoint> m){
		for(MatOfPoint i : m){
			int j = 1;
			Rect rectangle = Imgproc.boundingRect(i);
			int rectWidth = rectangle.width;
			int rectHeight = rectangle.height;
			int rectX = (int) (.5 * rectangle.width + rectangle.x);
			int rectY = (int) (.5 * rectangle.height + rectangle.y);
			int xDistanceFromCenter = Math.abs(centerX - rectX);
			int yDistanceFromCenter = Math.abs(centerY - rectY);
			SmartDashboard.putNumber("Contour " + j +" X Value", rectX);
			SmartDashboard.putNumber("Contour " + j +" Y Value", rectY);
			SmartDashboard.putNumber("Contour " + j +" X Offset", xDistanceFromCenter);
			SmartDashboard.putNumber("Contour " + j +" Y Offset", yDistanceFromCenter);
			SmartDashboard.putNumber("Contour " + j + " Width", rectWidth);
			SmartDashboard.putNumber("Contour " + j++ + " Height", rectHeight);
		}
	}
    public int getActiveButtons(){
    	int count = 0;
    	for(int i = 1; i < 14; i++){						//cycles through all possible buttons
    		if(gamepad.getRawButton(i)){				//checks for active button
    			count = i;									//sets count to the highest button value being depressed
    		}
    	}
		return count;										//returns the highest depressed button number
    }
}
