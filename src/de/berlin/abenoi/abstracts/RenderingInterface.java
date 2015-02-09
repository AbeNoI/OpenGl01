package de.berlin.abenoi.abstracts;

public interface RenderingInterface {
	/**
	 * doing all initializations here (OpenGl, objects,lights, textures, shaders, ....
	 */
	public abstract void initOpenGl();
	/**
	 * updating the entier scene according to a time delta to the last frame processed
	 * @param delta the time difference between two frames
	 */
	public abstract void update(int delta);
	/**
	 * rendering the entire scene (all objects)
	 */
	public abstract void render();
}
