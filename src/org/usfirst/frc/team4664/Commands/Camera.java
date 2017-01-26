package org.usfirst.frc.team4664.Commands;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Camera {
	public Camera(){
        new Thread(() -> {
            UsbCamera kanouSekaiRon = CameraServer.getInstance().startAutomaticCapture();
            kanouSekaiRon.setResolution(480, 360);
            
            CvSink akagami = CameraServer.getInstance().getVideo();
            CvSource possibleWorldTheory = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat nayu = new Mat();
            Mat gorou = new Mat();
            
            while(!Thread.interrupted()) {
            	akagami.grabFrame(nayu);
                Imgproc.cvtColor(nayu, gorou, Imgproc.COLOR_BGR2GRAY);
                possibleWorldTheory.putFrame(gorou);
            }
        }).start();
	}
}
