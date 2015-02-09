package de.berlin.abenoi.abstracts;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	
	public float rotFactorX, rotFactorY, rotFactorZ;
	private int percent;

	public Input() {
		rotFactorX = 0; rotFactorY = 0;rotFactorZ = 0;
		percent= 0;
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



