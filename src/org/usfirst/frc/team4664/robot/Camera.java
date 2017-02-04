package org.usfirst.frc.team4664.robot;


import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team4664.Commands.Pipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera {		
	UsbCamera camera;
	CvSink cvSink;
	CvSource cvSource;
	Pipeline tPipe;
	public final int centerX = 320;
	public final int centerY = 240;
	public static VideoCapture videoCapture;
	public Camera(){		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(480, 360);
            
        cvSink = CameraServer.getInstance().getVideo();						//video
        cvSource = CameraServer.getInstance().putVideo("Theory", 640, 480);	//viewer

    }
	public void run(){
        while(true) {
        	Mat source = new Mat();											//source image
        	Mat somethingidk = new Mat();									//unused image
        	ArrayList<MatOfPoint> test = new ArrayList<MatOfPoint>();		//array of shapes(i.e. contours/blobs/rectangles)
        	tPipe = new Pipeline();											//CV pipeline(camera processor)

        	cvSink.grabFrame(source);										//set source image to Video feed frame
        	tPipe.process(source);											//process source image
        	Imgproc.drawContours(source, tPipe.filterContoursOutput(), 10, new Scalar(0,255,255));
        	cvSource.putFrame(source);										//view processed image
        	obtainValues(test);
        }
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
}
