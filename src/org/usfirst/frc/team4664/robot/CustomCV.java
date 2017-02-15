package org.usfirst.frc.team4664.robot;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
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
		Point[][] shapes = filterOutliers(lines);
		int shapeIndex = filterShapes(shapes)[0];
		for(int i = 0; i <= shapes[filterShapes(shapes)[0]].length; i += 2){
			counter[0] += shapes[shapeIndex][i].x;
			counter[1] += shapes[shapeIndex][i].y;
			counter[3]++;
			Imgproc.line(img, shapes[shapeIndex][i], shapes[shapeIndex][i++], new Scalar(0, 0, 255));
		}
		shapeIndex = filterShapes(shapes)[1];
		for(int i = 0; i <= shapes[filterShapes(shapes)[0]].length; i += 2){
			counter[0] += shapes[shapeIndex][i].x;
			counter[1] += shapes[shapeIndex][i].y;
			counter[3]++;
			Imgproc.line(img, shapes[shapeIndex][i], shapes[shapeIndex][i++], new Scalar(0, 0, 255));
		}
		
//		Point[] points = new Point[]{new Point(), new Point()};
//		for(int i = 0; i < lines.rows(); i++){
//			double[] vec = lines.get(i, 0);
//			counter[0] += vec[0];
//			counter[1] += vec[1];
//			counter[0] += vec[2];
//			counter[1] += vec[3];
//			counter[2] += 2;
//			points[0].x = vec[0];
//			points[0].y = vec[1];
//			points[1].x = vec[2];
//			points[1].y = vec[3];
//			System.out.println(points[0].x);
//			System.out.println(points[0].y);
//			System.out.println(points[1].x);
//			System.out.println(points[1].y);
//			Imgproc.line(image, points[0], points[1], new Scalar(0,0,255));
//		}
		Imgproc.rectangle(image, new Point((int)(counter[0]/counter[2]) - 2, (int)(counter[0]/counter[2]) - 2), new Point((int)(counter[1]/counter[2]) + 2, (int)(counter[1]/counter[2]) + 2), new Scalar(0,0,255)); // draw a 4 pixel square around the center
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
	private Point[][] filterOutliers(Mat linesIn){
		Point shapes[][] = new Point[11][203];
		int[][] filterLines = new int[11][3];
		int shapesCounter = 0;
		int shapesPointCounter = 0;
		for(int i = 0, k = 0; i < 200; i++){
			if(i == 199){
				k++;
			}
			shapes[k][i] = new Point();
		}
		for(int i = 0; i < linesIn.rows(); i++){
			double[] vec = lines.get(i, 0);
			if(filterLines[shapesCounter][2] == 0){
				filterLines[shapesCounter][0] += vec[0] + vec[2];// X
				filterLines[shapesCounter][1] += vec[1] + vec[3];// Y
				filterLines[shapesCounter][2] += 2;
				shapes[shapesCounter][shapesPointCounter].x = vec[0];
				shapes[shapesCounter][shapesPointCounter++].y = vec[1];
				shapes[shapesCounter][shapesPointCounter].x = vec[2];
				shapes[shapesCounter][shapesPointCounter++].y = vec[3];
			}
			else if((Math.abs(filterLines[shapesCounter][0]/filterLines[shapesCounter][2] - (vec[0] + vec[2])) < 10) && (Math.abs(filterLines[shapesCounter][0]/filterLines[shapesCounter][2] - (vec[0] + vec[1])) < 10) || filterLines[shapesCounter][0] == 0){
				filterLines[shapesCounter][0] += vec[0] + vec[2];// X
				filterLines[shapesCounter][1] += vec[1] + vec[3];// Y
				filterLines[shapesCounter][2] += 2;
				shapes[shapesCounter][shapesPointCounter].x = vec[0];
				shapes[shapesCounter][shapesPointCounter++].y = vec[1];
				shapes[shapesCounter][shapesPointCounter].x = vec[2];
				shapes[shapesCounter][shapesPointCounter++].y = vec[3];
			}
			else if(shapesCounter < 11){
				shapesCounter++;				
				filterLines[shapesCounter][0] += vec[0] + vec[2];// X
				filterLines[shapesCounter][1] += vec[1] + vec[3];// Y
				filterLines[shapesCounter][2] += 2;
				System.out.println(shapesPointCounter);
				shapes[shapesCounter][shapesPointCounter].x = (int)vec[0];
				shapes[shapesCounter][shapesPointCounter++].y = (int)vec[1];
				shapes[shapesCounter][shapesPointCounter].x = (int)vec[2];
				shapes[shapesCounter][shapesPointCounter++].y = (int)vec[3];
			}
		}
		return shapes;
	}
	private int[] filterShapes(Point[][] shapesIn){
		int[] index = new int[2];
		for(int i = 0, count = 0; i < shapesIn.length; i++){
			if(i == 0){
				index[count++] = shapesIn[i].length;	//lines in a shape
			}
			else if(index[1] <= shapesIn[i].length && index[0] >= shapesIn[i].length){	//if Index 1 is less than the new number, but index 0 is larger than the new number, place the new number in index 1;
				index[1] = i;
			}
			else if(index[0] <= shapesIn[i].length && index[1] >= shapesIn[i].length){	//if Index 1 is larger than the new number, but index 0 is less than the new number, place the new number in index 1;
				index[1] = i;
			}
			else if(index[0] > index[1] && shapesIn[i].length >= index[0] && shapesIn[i].length >= index[1]){
				index[1] = i;
			}
			else if(index[1] > index[0] && shapesIn[i].length >= index[0] && shapesIn[i].length >= index[1]){
				index[0] = i;
			}
			else{
			}
		}
		return index;
	}
}
