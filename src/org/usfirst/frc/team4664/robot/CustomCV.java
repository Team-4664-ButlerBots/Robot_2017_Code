package org.usfirst.frc.team4664.robot;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.LineSegmentDetector;
import org.opencv.imgcodecs.Imgcodecs;

public class CustomCV {
	Mat edges, lines, image, sourceImage;
	public int intersectionsThreshold = 40;
	public double min_pixel = 50, max_pixel = 200, pixels = 5;
	public int[] counter;
	public CustomCV(){
		counter = new int[3];	//[0] = x counter [1] = y counter [2] = point counter
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		sourceImage = Imgcodecs.imread("M:\\Programming\\2017VisionExample\\Vision Images\\LED Peg\\Peg3.jpg");
	    Imgcodecs.imwrite("M:\\Programming\\lines1.jpg", houghDetector(sourceImage));
	    Imgcodecs.imwrite("M:\\Programming\\lines2.jpg", hsl(sourceImage));
		
//		for(int i = 3; i < 18; i++){
//			sourceImage = Imgcodecs.imread("M:\\Programming\\2017VisionExample\\Vision Images\\LED Peg\\Peg" + i + ".jpg");
//		    Imgcodecs.imwrite("M:\\Programming\\lines" + i + ".jpg", houghDetector(sourceImage));
//		    Imgcodecs.imwrite("M:\\Programming\\lines" + (i + 17) + ".jpg", hsl(sourceImage));
//		}
	}
	public static void main(String[] args){
		CustomCV cv = new CustomCV();
	}
	/**
	 * @param img
	 * @return
	 */
	public Point getCenter(Mat image){
		counter[0] = 0;
		counter[1] = 0;
		counter[2] = 0;
		Mat imgGrey = new Mat();
		Point center = new Point();
		imgGrey = hsl(image);
		//System.out.println(imgGrey.cols());
		//System.out.println(imgGrey.rows());
		//System.out.println(imgGrey.size());
		for(int i = 0; i < imgGrey.rows(); i++){
			for(int j = 0; j < imgGrey.cols(); j++){
				double[] test = imgGrey.get(i, j);
				if(test[0] != 0){
					//System.out.println(test[0]);
					counter[0] += i;	//x
					counter[1] += j;	//y
					counter[2]++;	//counter
				}
			}
		}
		center.x = counter[0]/counter[2];
		center.y = counter[1]/counter[2];
	    //Imgcodecs.imwrite("M:\\Programming\\lines1.jpg", imgGrey);
		//Imgproc.rectangle(image, new Point((int)(counter[0]/counter[2]) - 2, (int)(counter[0]/counter[2]) - 2), new Point((int)(counter[1]/counter[2]) + 2, (int)(counter[1]/counter[2]) + 2), new Scalar(0,0,255)); // draw a 4 pixel square around the center
		return center;
	}
	public MatOfPoint[] sortShapes(Mat image){
		ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Pipeline tP = new Pipeline();
		tP.process(image);
		contours = tP.findContoursOutput();
		System.out.println(contours.size());
		MatOfPoint[] contoursOrdered = new MatOfPoint[2];
		ArrayList<Double> contourArea = new ArrayList<Double>();
		for (int i = 0; i < contours.size(); i++) {
			MatOfPoint contour = contours.get(i);
			Rect bb = Imgproc.boundingRect(contour);
			if (bb.width < 5 ) continue;
			if (bb.height < 5 ) continue;
			contourArea.add(bb.area());
		}
		if(contourArea.size() > 2){
			while(contourArea.get(0) < contourArea.get(1) || contourArea.get(1) < contourArea.get(2)){
				for(int i = 0; i < contourArea.size()-1; i++){
					//System.out.println(contourArea.size()); 
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
		else if(contourArea.size() == 2){
			if(contourArea.get(0) > contourArea.get(1)){
				contoursOrdered[0] = contours.get(0);
				contoursOrdered[1] = contours.get(1);
			}
			else{
				contoursOrdered[0] = contours.get(1);
				contoursOrdered[1] = contours.get(0);
			}
			return contoursOrdered;
		}
		else if(contourArea.size() == 1){
			contoursOrdered[0] = contours.get(0);
			return contoursOrdered;
		}
		else{
			return contoursOrdered;
		}
	}
	public void drawRect(Mat image) {
		MatOfPoint[] shapes = sortShapes(image);
		if(shapes != null){
			if(shapes.length == 2){
				Rect rect0 = Imgproc.boundingRect(shapes[0]);
				Rect rect1 = Imgproc.boundingRect(shapes[1]);
				Imgproc.rectangle(image, rect0.tl(), rect0.br(), new Scalar(0, 120, 255));
				Imgproc.rectangle(image, rect1.tl(), rect1.br(), new Scalar(0, 120, 255));
			}
			else if(sortShapes(image).length == 1){
				Rect rect0 = Imgproc.boundingRect(sortShapes(image)[0]);
				Imgproc.rectangle(image, rect0.tl(), rect0.br(), new Scalar(0, 120, 255));
			}
		}
	}
	public Mat houghDetector(Mat image){
		Mat imgGrey = new Mat();
		edges = new Mat();
		lines = new Mat();
		this.image = image;
		imgGrey = hsl(this.image);
		//Imgproc.cvtColor(img, imgGrey,  Imgproc.COLOR_RGB2GRAY);
		Imgproc.Canny(imgGrey, edges, min_pixel, max_pixel); 
		Imgproc.HoughLinesP(edges, lines, pixels, Math.toRadians(1), intersectionsThreshold); 
		//System.out.println(lines.cols());
		//System.out.println(lines.rows());
		System.out.println(lines.size());
		Point[] points = new Point[]{new Point(), new Point()};
		List<Double> slope = new ArrayList<Double>();
		for(int i = 0; i < lines.rows(); i++){
			double[] vec = lines.get(i, 0);
			points[0].x = vec[0];
			points[0].y = vec[1];
			points[1].x = vec[2];
			points[1].y = vec[3];
			//System.out.println((double)(points[0].x - points[1].x));
			//System.out.println((double)(points[0].y - points[1].y));
			slope.add(Math.toDegrees(Math.atan((double)(points[0].y - points[1].y) / (points[0].x - points[1].x))));
			//System.out.println(points[0].x);
			//System.out.println(points[0].y);
			//System.out.println(points[1].x);
			//System.out.println(points[1].y);
			//Imgproc.line(image, points[0], points[1], new Scalar(0,0,255));
		}
		Collections.sort(slope);
		for(double i: slope){
			System.out.println(i);
		}
		Point center = getCenter(this.image);
		//System.out.println(center.x);
		//System.out.println(center.y);
		Point centerOffset = new Point(center.x + 4, center.y + 4);
		Imgproc.rectangle(this.image, center, centerOffset, new Scalar(0,0,255)); // draw a 4 pixel square around the center
		drawRect(this.image);
		return this.image;
	}	
	public static class Line {
		public final double x1, y1, x2, y2;
		public Line(double x1, double y1, double x2, double y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		public double lengthSquared() {
			return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
		}
		public double length() {
			return Math.sqrt(lengthSquared());
		}
		public double angle() {
			return Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
		}
	}
	/**
	 * Finds all line segments in an image.
	 * @param input The image on which to perform the find lines.
	 * @param lineList The output where the lines are stored.
	 */
	private void findLines(Mat input, ArrayList<Line> lineList) {
		final LineSegmentDetector lsd = Imgproc.createLineSegmentDetector();
		final Mat lines = new Mat();
		lineList.clear();
		if (input.channels() == 1) {
			lsd.detect(input, lines);
		} else {
			final Mat tmp = new Mat();
			Imgproc.cvtColor(input, tmp, Imgproc.COLOR_BGR2GRAY);
			lsd.detect(tmp, lines);
		}
		if (!lines.empty()) {
			for (int i = 0; i < lines.rows(); i++) {
				lineList.add(new Line(lines.get(i, 0)[0], lines.get(i, 0)[1],
					lines.get(i, 0)[2], lines.get(i, 0)[3]));
			}
		}
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
