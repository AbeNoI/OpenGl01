package templates;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;

import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;


public class Quad {
	
	float texCoords[] = new float[8];
	float colorCoordsR[] = new float[8];
	float colorCoordsG[] = new float[8];
	float colorCoordsB[] = new float[8];
	float objectCoordsX[] = new float[24];
	float objectCoordsY[] = new float[24];
	float objectCoordsZ[] = new float[24];
	float normalsX[] = new float[6];
	float normalsY[] = new float[6];
	float normalsZ[] = new float[6];
	
	private float[] spinValues = new float[]{0f,0f,0f};
	private float[] distanceToZero = new float[]{0f,0f,0f};
	private float rotationSpeed;
	private float[] rotationValues;
	private long ownSpin;
	ArrayList<Quad> list = new ArrayList<Quad>();
	
	private int shaderNormalId, shaderProzeduralId;
	private float colCounter;
	private float lightCounter;
	
	private Texture texture;
	private Texture normalTexture;

	public Quad(float length) {
		initializeObject();
		uniformScale(length);
	}
	
	private void uniformScale(float f){
		for(int i=0; i<8; i++){
			objectCoordsX[i] *= f;
			objectCoordsY[i] *= f;
			objectCoordsZ[i] *= f;
		}
		
	}
	public void translateZ(float f){
		for(int i=0; i<8; i++){
			objectCoordsZ[i] += f;
			
		}
		
	}
	public void initializeObject(){
		texCoords = new float[8];
		colorCoordsR = new float[8];
		colorCoordsG = new float[8];
		colorCoordsB = new float[8];
		objectCoordsX = new float[8];
		objectCoordsY = new float[8];
		objectCoordsZ = new float[8];
		normalsX = new float[6];
		normalsY = new float[6];
		normalsZ = new float[6];
		
							//				   
							//				  4________5
		      				//				  / top	  /|		 
		normalsX[0] = 0;	// front	    0/_______/1|
		normalsX[1] = 1; 	// right		|  |    |  |
		normalsX[2] = 0; 	// back			|  |7___|__|6
		normalsX[3] = -1;  	// left 		| /	bot | /
		normalsX[4] = 0;    // top		   3|/______|/2
		normalsX[5] = 0;  	// bottom
		
		normalsY[0] = 0;
		normalsY[1] = 0; 
		normalsY[2] = 0; 
		normalsY[3] = 0; 
		normalsY[4] = 1;   
		normalsY[5] = -1; 
		
		normalsZ[0] = 1;
		normalsZ[1] = 0; 
		normalsZ[2] = -1; 
		normalsZ[3] = 0; 
		normalsZ[4] = 0;   
		normalsZ[5] = 0; 
		
		texCoords[0] = 0;   
		texCoords[1] = 1; 
		texCoords[2] = 1; 
		texCoords[3] = 1; 
		texCoords[4] = 1;   
		texCoords[5] = 0; 
		texCoords[6] = 0; 
		texCoords[7] = 0; 
		
		colorCoordsR[0] = 0;   
		colorCoordsR[1] = 0; 
		colorCoordsR[2] = 0; 
		colorCoordsR[3] = 0;
		colorCoordsR[4] = 1;   
		colorCoordsR[5] = 1; 
		colorCoordsR[6] = 1; 
		colorCoordsR[7] = 1;
		
		colorCoordsG[0] = 0;   
		colorCoordsG[1] = 0; 
		colorCoordsG[2] = 1; 
		colorCoordsG[3] = 1;
		colorCoordsG[4] = 0;   
		colorCoordsG[5] = 0; 
		colorCoordsG[6] = 1; 
		colorCoordsG[7] = 1;
		
		colorCoordsB[0] = 0;   
		colorCoordsB[1] = 1; 
		colorCoordsB[2] = 0; 
		colorCoordsB[3] = 1;
		colorCoordsB[4] = 0;   
		colorCoordsB[5] = 1; 
		colorCoordsB[6] = 0; 
		colorCoordsB[7] = 1;
		
		objectCoordsX[0] = -1;   
		objectCoordsX[1] = 1; 
		objectCoordsX[2] = 1; 
		objectCoordsX[3] = -1; 
		objectCoordsX[4] = -1;   
		objectCoordsX[5] = 1; 
		objectCoordsX[6] = 1; 
		objectCoordsX[7] = -1;
		
		objectCoordsY[0] = 1;   
		objectCoordsY[1] = 1; 
		objectCoordsY[2] = -1; 
		objectCoordsY[3] = -1; 
		objectCoordsY[4] = 1;   
		objectCoordsY[5] = 1; 
		objectCoordsY[6] = -1; 
		objectCoordsY[7] = -1;
		
		
		objectCoordsZ[0] = 1;   
		objectCoordsZ[1] = 1; 
		objectCoordsZ[2] = 1; 
		objectCoordsZ[3] = 1; 
		objectCoordsZ[4] = -1;   
		objectCoordsZ[5] = -1; 
		objectCoordsZ[6] = -1; 
		objectCoordsZ[7] = -1;
	}
	
	public void drawObject(){

		//glPushMatrix();
		OwnGL.pushMatrix();
		//glTranslatef(distanceToZero[0],distanceToZero[1],distanceToZero[2]);
		OwnGL.translate(distanceToZero[0],distanceToZero[1],distanceToZero[2]);
		
		
//		glRotatef(ownSpin*rotationSpeed,spinValues[0], spinValues[1],spinValues[2]);
		OwnGL.rotateX(ownSpin*rotationSpeed*spinValues[0]);
		OwnGL.rotateY(ownSpin*rotationSpeed*spinValues[1]);
		OwnGL.rotateZ(ownSpin*rotationSpeed*spinValues[2]);
//		glScalef(0.4f,0.4f,0.4f);
		OwnGL.scale(0.4f,0.4f,0.4f);
//		glRotatef(ownSpin*0.2f, rotationValues[0],rotationValues[1],rotationValues[2]);
		
		
		GL20.glUseProgram(shaderProzeduralId);
		GL20.glUniform1f(GL20.glGetUniformLocation(shaderProzeduralId, "colorCounter"),((float)Math.sin(colCounter)));
		GL20.glUniform3f(GL20.glGetUniformLocation(shaderProzeduralId, "lightPos"),2 *(float)Math.sin(lightCounter), 1, 10);
		GL20.glUniformMatrix4(GL20.glGetUniformLocation(shaderProzeduralId ,"mvMatrix"), false, OwnGL.getMVMatrixAsBuffer());
		GL20.glUniformMatrix4(GL20.glGetUniformLocation(shaderProzeduralId ,"mvpMatrix"), false, OwnGL.getMVPMatrixAsBuffer());
		
		
		OwnGL.rotateX(ownSpin*0.2f*spinValues[0]*rotationValues[0]);
		OwnGL.rotateY(ownSpin*0.2f*spinValues[1]*rotationValues[1]);
		OwnGL.rotateZ(ownSpin*0.2f*spinValues[2]*rotationValues[2]);

		if(!list.isEmpty()){
			//for(Quad q : list)q.drawObject();
		}
		
//		glRotatef(ownSpin*0.2f, rotationValues[0],rotationValues[1],rotationValues[2]);
//		OwnGL.rotateX(ownSpin*0.2f*rotationValues[0]);
//		OwnGL.rotateY(ownSpin*0.2f*rotationValues[1]);
//		OwnGL.rotateZ(ownSpin*0.2f*rotationValues[2]);
		
		drawAllPoints();
		
		OwnGL.popMatrix();
	}


	private void drawAllPoints() {
		glBegin(GL_QUADS);
		
		glNormal3f(normalsX[0], normalsY[0], normalsZ[0]);
		
		glTexCoord2f(texCoords[0],texCoords[1]);
		glColor3f(colorCoordsR[0],colorCoordsG[0],colorCoordsB[0]);
		glVertex3f(objectCoordsX[3],objectCoordsY[3],objectCoordsZ[3]);
		
		glTexCoord2f(texCoords[2],texCoords[3]);
		glColor3f(colorCoordsR[3],colorCoordsG[3],colorCoordsB[3]);
		glVertex3f(objectCoordsX[2],objectCoordsY[2],objectCoordsZ[2]);
		
		glTexCoord2f(texCoords[4],texCoords[5]);
		glColor3f(colorCoordsR[2],colorCoordsG[2],colorCoordsB[2]);
		glVertex3f(objectCoordsX[1],objectCoordsY[1],objectCoordsZ[1]);
		
		glTexCoord2f(texCoords[6],texCoords[7]);
		glColor3f(colorCoordsR[1],colorCoordsG[1],colorCoordsB[1]);
		glVertex3f(objectCoordsX[0],objectCoordsY[0],objectCoordsZ[0]);
		
		// right
		
		glNormal3f(normalsX[1], normalsY[1], normalsZ[1]);
		
		glTexCoord2f(texCoords[0],texCoords[1]);
		glColor3f(colorCoordsR[2],colorCoordsG[2],colorCoordsB[2]);
		glVertex3f(objectCoordsX[2],objectCoordsY[2],objectCoordsZ[2]);
		
		glTexCoord2f(texCoords[2],texCoords[3]);
		glColor3f(colorCoordsR[6],colorCoordsG[6],colorCoordsB[6]);
		glVertex3f(objectCoordsX[6],objectCoordsY[6],objectCoordsZ[6]);
		
		glTexCoord2f(texCoords[4],texCoords[5]);
		glColor3f(colorCoordsR[5],colorCoordsG[5],colorCoordsB[5]);
		glVertex3f(objectCoordsX[5],objectCoordsY[5],objectCoordsZ[5]);
		
		glTexCoord2f(texCoords[6],texCoords[7]);
		glColor3f(colorCoordsR[1],colorCoordsG[1],colorCoordsB[1]);
		glVertex3f(objectCoordsX[1],objectCoordsY[1],objectCoordsZ[1]);
			
		// back
		
		glNormal3f(normalsX[2], normalsY[2], normalsZ[2]);
		
		glTexCoord2f(texCoords[0],texCoords[1]);
		glColor3f(colorCoordsR[6],colorCoordsG[6],colorCoordsB[6]);
		glVertex3f(objectCoordsX[6],objectCoordsY[6],objectCoordsZ[6]);
		
		glTexCoord2f(texCoords[2],texCoords[3]);
		glColor3f(colorCoordsR[7],colorCoordsG[7],colorCoordsB[7]);
		glVertex3f(objectCoordsX[7],objectCoordsY[7],objectCoordsZ[7]);
		
		glTexCoord2f(texCoords[4],texCoords[5]);
		glColor3f(colorCoordsR[4],colorCoordsG[4],colorCoordsB[4]);
		glVertex3f(objectCoordsX[4],objectCoordsY[4],objectCoordsZ[4]);
		
		glTexCoord2f(texCoords[6],texCoords[7]);
		glColor3f(colorCoordsR[5],colorCoordsG[5],colorCoordsB[5]);
		glVertex3f(objectCoordsX[5],objectCoordsY[5],objectCoordsZ[5]);
		
		// left
		
		glNormal3f(normalsX[3], normalsY[3], normalsZ[3]);
		
		glTexCoord2f(texCoords[0],texCoords[1]);
		glColor3f(colorCoordsR[7],colorCoordsG[7],colorCoordsB[7]);
		glVertex3f(objectCoordsX[7],objectCoordsY[7],objectCoordsZ[7]);
		
		glTexCoord2f(texCoords[2],texCoords[3]);
		glColor3f(colorCoordsR[3],colorCoordsG[3],colorCoordsB[3]);
		glVertex3f(objectCoordsX[3],objectCoordsY[3],objectCoordsZ[3]);
		
		glTexCoord2f(texCoords[4],texCoords[5]);
		glColor3f(colorCoordsR[0],colorCoordsG[0],colorCoordsB[0]);
		glVertex3f(objectCoordsX[0],objectCoordsY[0],objectCoordsZ[0]);
		
		glTexCoord2f(texCoords[6],texCoords[7]);
		glColor3f(colorCoordsR[4],colorCoordsG[4],colorCoordsB[4]);
		glVertex3f(objectCoordsX[4],objectCoordsY[4],objectCoordsZ[4]);
		
		// top
		
		glNormal3f(normalsX[4], normalsY[4], normalsZ[4]);
		
		glTexCoord2f(texCoords[0],texCoords[1]);
		glColor3f(colorCoordsR[0],colorCoordsG[0],colorCoordsB[0]);
		glVertex3f(objectCoordsX[0],objectCoordsY[0],objectCoordsZ[0]);
		
		glTexCoord2f(texCoords[2],texCoords[3]);
		glColor3f(colorCoordsR[1],colorCoordsG[1],colorCoordsB[1]);
		glVertex3f(objectCoordsX[1],objectCoordsY[1],objectCoordsZ[1]);
		
		glTexCoord2f(texCoords[4],texCoords[5]);
		glColor3f(colorCoordsR[5],colorCoordsG[5],colorCoordsB[5]);
		glVertex3f(objectCoordsX[5],objectCoordsY[5],objectCoordsZ[5]);
		
		glTexCoord2f(texCoords[6],texCoords[7]);
		glColor3f(colorCoordsR[4],colorCoordsG[4],colorCoordsB[4]);
		glVertex3f(objectCoordsX[4],objectCoordsY[4],objectCoordsZ[4]);
		
		// bottom
		
		glNormal3f(normalsX[5], normalsY[5], normalsZ[5]);
		
		glTexCoord2f(texCoords[0],texCoords[1]);
		glColor3f(colorCoordsR[7],colorCoordsG[7],colorCoordsB[7]);
		glVertex3f(objectCoordsX[7],objectCoordsY[7],objectCoordsZ[7]);
		
		glTexCoord2f(texCoords[2],texCoords[3]);
		glColor3f(colorCoordsR[6],colorCoordsG[6],colorCoordsB[6]);
		glVertex3f(objectCoordsX[6],objectCoordsY[6],objectCoordsZ[6]);
		
		glTexCoord2f(texCoords[4],texCoords[5]);
		glColor3f(colorCoordsR[2],colorCoordsG[2],colorCoordsB[2]);
		glVertex3f(objectCoordsX[2],objectCoordsY[2],objectCoordsZ[2]);
		
		glTexCoord2f(texCoords[6],texCoords[7]);
		glColor3f(colorCoordsR[3],colorCoordsG[3],colorCoordsB[3]);
		glVertex3f(objectCoordsX[3],objectCoordsY[3],objectCoordsZ[3]);
		glEnd();
	}

	public float[] getSpinValues() {
		return spinValues;
	}
	public void setSpinValues(float[] values) {
		this.spinValues = values;
	}
	
	public float[] getDistanceToZero() {
		return distanceToZero;
	}

	public void setDistanceToZero(float[] distanceToZero) {
		this.distanceToZero = distanceToZero;
	}

	public float getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(float rotation) {
		this.rotationSpeed = rotation;
	}

	public float getOwnSpin() {
		return ownSpin;
	}

	public void setOwnSpin(long ownSpin) {
		this.ownSpin = ownSpin;
	}

	public float[] getRotationValues() {
		return rotationValues;
	}

	public void setRotationValues(float[] rotationValues) {
		this.rotationValues = rotationValues;
	}

	public float getColCounter() {
		return colCounter;
	}

	public void setColCounter(float colCounter) {
		this.colCounter = colCounter;
		for(Quad q : this.list){
			q.setColCounter(colCounter);
		}
	}

	public float getLightCounter() {
		return lightCounter;
	}

	public void setLightCounter(float lightCounter) {
		this.lightCounter = lightCounter;
		for(Quad q : this.list){
			q.setLightCounter(lightCounter);
		}
	}	

	public void setTextureIds(int shaderNormalId, int shaderProzeduralId) {
		this.shaderNormalId = shaderNormalId;
		this.shaderProzeduralId = shaderProzeduralId;
		
		for(Quad q : this.list){
			q.setTextureIds(shaderNormalId, shaderProzeduralId);
		}
	}

	public void setTetures(Texture texture, Texture normalTexture) {
		this.texture = texture;
		this.normalTexture = normalTexture;
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);//Setzt 0te Texture
		glBindTexture(GL_TEXTURE_2D, this.texture.getId()); 
		
		GL13.glActiveTexture(GL13.GL_TEXTURE1);//Setzt 1te Texture
		glBindTexture(GL_TEXTURE_2D, this.normalTexture.getId());
	}




}
