package de.berlin.abenoi.abstracts;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Class Simble is an abstract class with sequence of method calls when 
 * creating a game for example
 * @author Abe_No_I
 * @date 09.02.2015
 *
 */
public abstract class Simple implements RenderingInterface{ 
	
	/**
	 * extendable class constructs a Display of given size
	 * @param width the application width in px
	 * @param height the application height in px
	 */
	public Simple(int width, int height){
		
		try {
			// creating new Display(widthxheight)
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			
		} catch (LWJGLException e) {
			
			e.printStackTrace();
		}
		// init OpenGL
		initOpenGl();
		// call before loop to initialise fps timer
		FpsUtils.getDelta();// call before loop to initialise fps timer
		
	}

	
	/**
	 * starting the application, would be called within main()
	 */
	public void start(){
		
		
		while(!Display.isCloseRequested()){
			//do-while not closing application
			
			int delta = FpsUtils.getDelta();
			
			// change delta according to global behavior
			// setting diffent speed within multiplier within objects
			
			update(delta); 
			render();
			
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
			
		Display.destroy(); // close Window
		
	}
}
	


