package org.usfirst.frc.team4664.robot;

import java.util.ArrayList;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

public class CustomCV {
	Mat edges, lines;
	public int intersectionsThreshold = 80;
	public double min_pixel = 50, max_pixel = 200, pixels = 20;
	public CustomCV(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat image = Imgcodecs.imread("M:\\Programming\\2017VisionExample\\Vision Images\\LED Peg\\1ftH3ftD2Angle0Brightness.jpg");
	    Imgcodecs.imwrite("M:\\Programming\\lines.jpg", houghDetector(image));
	}
	public static void main(String[] args){
		CustomCV cv = new CustomCV();
	}
	public Mat houghDetector(Mat img){
		Mat imgGrey = new Mat();
		edges = new Mat();
		lines = new Mat();
		Imgproc.cvtColor(img, imgGrey,  Imgproc.COLOR_RGB2GRAY);
		Imgproc.Canny(imgGrey, edges, min_pixel, max_pixel); 
		Imgproc.HoughLinesP(edges, lines, pixels, Math.toRadians(1), intersectionsThreshold); 
		System.out.println(lines.cols());
		System.out.println(lines.rows());
		System.out.println(lines.size());
		Point[] points = new Point[]{new Point(), new Point()};
		for(int i = 0; i < lines.rows(); i++){
			double[] vec = lines.get(i, 0);
			points[0].x = vec[0];
			points[0].y = vec[1];
			points[1].x = vec[2];
			points[1].y = vec[3];
			System.out.println(points[0].x);
			System.out.println(points[0].y);
			System.out.println(points[1].x);
			System.out.println(points[1].y);
			Imgproc.line(img, points[0], points[1], new Scalar(0,0,255));
		}
		return img;
	}
}
