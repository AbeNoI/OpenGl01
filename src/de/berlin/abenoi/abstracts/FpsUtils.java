package de.berlin.abenoi.abstracts;

import org.lwjgl.Sys;

public abstract class FpsUtils {
	
	public static float fps;		  // number of frames per second
	
	private static long lastFrameTime; // timestamp type long
	private static float lastFPS;	   // 	
	

	public static int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrameTime);
		lastFrameTime = time;
		
		return delta;
	}
	
	private static long getTime() {
		return (System.currentTimeMillis());
	}
	
	public static void updateFPS(){
		long timeStamp = getTime();
		if (timeStamp - lastFPS > 1000){
			// reset FPS Counter
			fps = 0 ;
			lastFPS += 1000;
		}
		fps++;
	}
}
