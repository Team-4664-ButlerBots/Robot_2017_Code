package org.usfirst.frc.team4664.robot;

import java.util.ArrayList;
import java.util.Vector;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class CustomCV {
	Mat edges, edgesGrey, lines;
	public int min_pixel = 0, max_pixel = 100, intersectionsThreshold = 2;
	public double pixels = 1;
	public CustomCV(){
	}
	public Mat houghDetector(Mat img){
		Imgproc.Canny(img, edges, min_pixel, max_pixel); 
		Imgproc.cvtColor(edges, edgesGrey,  Imgproc.COLOR_GRAY2BGR);
		Imgproc.HoughLinesP(edgesGrey, lines, pixels, Math.toRadians(1), intersectionsThreshold); 
		return lines;
	}
}
