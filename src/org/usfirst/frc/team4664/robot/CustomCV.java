package org.usfirst.frc.team4664.robot;


import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

public class CustomCV {
	Mat edges, lines, image;
	public int intersectionsThreshold = 10;
	public double min_pixel = 50, max_pixel = 200, pixels = 30;
	public int[] counter;
	public CustomCV(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		image = Imgcodecs.imread("M:\\Programming\\2017VisionExample\\Vision Images\\LED Peg\\1ftH3ftD2Angle0Brightness.jpg");
	    Imgcodecs.imwrite("M:\\Programming\\lines.jpg", houghDetector(image));
	}
	public static void main(String[] args){
		CustomCV cv = new CustomCV();
	}
	/**
	 * @param img
	 * @return
	 */
	public Point getCenter(Mat inImg){
		counter = new int[3];				//[0] = x counter [1] = y counter [2] = point counter
		Mat imgGrey = new Mat();
		Point center = new Point();
		imgGrey = hsl(inImg);
		System.out.println(imgGrey.cols());
		System.out.println(imgGrey.rows());
		System.out.println(imgGrey.size());
		Point[] points = new Point[]{new Point(), new Point()};
		for(int i = 0; i < imgGrey.rows(); i++){
			for(int j = 0; j < imgGrey.cols(); j++){
				double[] test = imgGrey.get(i, j);
				if(test[0] != 0){
					System.out.println(test[0]);
					counter[0] += i;	//x
					counter[1] += i;	//y
					counter[2] += 1;	//counter
				}
			}
		}
		center.x = counter[0]/counter[2];
		center.y = counter[1]/counter[2];
		Point centerOffset = new Point(center.x + 4, center.y + 4);
		Imgproc.rectangle(imgGrey, center, centerOffset, new Scalar(0,0,0));
	    Imgcodecs.imwrite("M:\\Programming\\lines1.jpg", imgGrey);
		//Imgproc.rectangle(image, new Point((int)(counter[0]/counter[2]) - 2, (int)(counter[0]/counter[2]) - 2), new Point((int)(counter[1]/counter[2]) + 2, (int)(counter[1]/counter[2]) + 2), new Scalar(0,0,255)); // draw a 4 pixel square around the center
		return center;
	}
	public MatOfPoint[] sortShapes(Mat input){
		ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Pipeline tP = new Pipeline();
		tP.process(input);
		contours = tP.findContoursOutput();
//		Mat hierarchy = new Mat();
//		contours.clear();
//		int mode = Imgproc.RETR_EXTERNAL;
//		int method = Imgproc.CHAIN_APPROX_SIMPLE;
//		Imgproc.findContours(input, contours, hierarchy, mode, method);
		MatOfPoint[] contoursOrdered = new MatOfPoint[2];
		ArrayList<Double> contourArea = new ArrayList<Double>();
		for (int i = 0; i < contours.size(); i++) {
			MatOfPoint contour = contours.get(i);
			Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < 5 ) continue;
			if (bb.height < 5 ) continue;
			contourArea.add(bb.area());
		}
		while(contourArea.get(0) < contourArea.get(1) && contourArea.get(1) < contourArea.get(2)){
			for(int i = 0; i < contourArea.size()-1; i++){
				System.out.println(contourArea.size()); 
				if(contourArea.get(i) < contourArea.get(i+1)){
					contourArea.set(i, contourArea.set(i+1, contourArea.get(i)));
				}
			}
		}
		for(int i = 0; i < contours.size(); i++){
			MatOfPoint contour = contours.get(i);
			Rect bb = Imgproc.boundingRect(contour);
			if(bb.area() == contourArea.get(0)){
				contoursOrdered[0] = contours.get(i);
			}
			if(bb.area() == contourArea.get(1)){
				contoursOrdered[1] = contours.get(i);
			}
		}
		return contoursOrdered;
	}
	public Mat drawRect(Mat input) {
		Rect rect0 = Imgproc.boundingRect(sortShapes(input)[0]);
		Rect rect1 = Imgproc.boundingRect(sortShapes(input)[1]);
		Imgproc.rectangle(input, rect0.tl(), rect0.br(), new Scalar(0, 255, 0));
		Imgproc.rectangle(input, rect1.tl(), rect1.br(), new Scalar(0, 255, 0));
		return input;
	}
	public Mat houghDetector(Mat img){
		counter = new int[3];				//[0] = x counter [1] = y counter [2] point counter
		Mat imgGrey = new Mat();
		Mat img2 = img;
		edges = new Mat();
		lines = new Mat();
		imgGrey = hsl(img2);
		//Imgproc.cvtColor(img, imgGrey,  Imgproc.COLOR_RGB2GRAY);
		Imgproc.Canny(imgGrey, edges, min_pixel, max_pixel); 
		Imgproc.HoughLinesP(edges, lines, pixels, Math.toRadians(1), intersectionsThreshold); 
		System.out.println(lines.cols());
		System.out.println(lines.rows());
		System.out.println(lines.size());
		Point[] points = new Point[]{new Point(), new Point()};
		for(int i = 0; i < lines.rows(); i++){
			double[] vec = lines.get(i, 0);
			counter[0] += vec[0];
			counter[1] += vec[1];//			counter[0] += vec[2];
			counter[1] += vec[3];
			counter[2] += 2;
			points[0].x = vec[0];
			points[0].y = vec[1];
			points[1].x = vec[2];
			points[1].y = vec[3];
			//System.out.println(points[0].x);
			//System.out.println(points[0].y);
			//System.out.println(points[1].x);
			//System.out.println(points[1].y);
			Imgproc.line(image, points[0], points[1], new Scalar(0,0,255));
		}
		Point center = getCenter(image);
		System.out.println(center.x);
		System.out.println(center.y);
		Point centerOffset = new Point(center.x + 4, center.y + 4);
		Imgproc.rectangle(image, center, centerOffset, new Scalar(0,0,255)); // draw a 4 pixel square around the center
		drawRect(image);
		return image;
	}
	private Mat hsl(Mat sourceimg){
		Mat hslThresholdOutput = new Mat();
		double[] hslThresholdHue = {72.84172661870504, 180.0};
		double[] hslThresholdSaturation = {151.34892086330936, 255.0};
		double[] hslThresholdLuminance = {48.156474820143885, 165.7935153583618};
		Imgproc.cvtColor(sourceimg, hslThresholdOutput, Imgproc.COLOR_BGR2HLS);
		Core.inRange(hslThresholdOutput, new Scalar(hslThresholdHue[0], hslThresholdLuminance[0], hslThresholdSaturation[0]), new Scalar(hslThresholdHue[1], hslThresholdLuminance[1], hslThresholdSaturation[1]), hslThresholdOutput);
		return hslThresholdOutput;
	}
}
