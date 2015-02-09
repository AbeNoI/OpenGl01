package templates;
import static org.lwjgl.opengl.GL11.glRotatef;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public abstract class Simple { //initialize Display
	protected abstract void initOpenGl();
	protected abstract void render();
	protected abstract void update(int delta);
	protected abstract int getDelta();
	public float rotFactorX = 0, rotFactorY = 0, rotFactorZ = 0;
	private int percent= 0;
	public Simple(){
	
		try {
			Display.setDisplayMode(new DisplayMode(800,600));//set Display
			Display.create();
		} catch (LWJGLException e) {
			
			e.printStackTrace();
		}
		
		initOpenGl();// init OpenGL
		getDelta();// call before loop to initialise fps timer
		
	}

	
	
	public void start(){
		
		
		while(!Display.isCloseRequested()){
			//do-while not closing application
			
			int delta = getDelta();
			delta += pollInputMouse(); //check for input
			
			
			update(delta); 
			render();
			
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
			
		Display.destroy(); // close Window
		
	}
	
    
	
	public int pollInputMouse() {
    	
		int returnX = 0;
		
        if (Mouse.isButtonDown(0)) {
		    returnX = Mouse.getX();
		    int y = Mouse.getY();
	 
		    int newPercent = (int) ((float) returnX / 8.0);
		     
		    if(newPercent != percent){
		    	System.out.println("current speed of global rotation :" + percent + "%");
		    	percent= newPercent;
		    }
        }
        return returnX;
	}
        
	public int pollInputKeyboard() { 
	
		int returnX = 4;
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
		    System.out.println("SPACE KEY IS DOWN");
		}
 
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		    	
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
				    returnX = 0;
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        	System.out.println("D Key Pressed");
		        	returnX = 1;
		        }
		    } else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		        	returnX = 2;
		        }
		    	if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		    		returnX = 3;
		    	}
		    }
		}
	
		return returnX;
	}
}


	


