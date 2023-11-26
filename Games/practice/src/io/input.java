package io;

import static org.lwjgl.glfw.GLFW.*;

import java.util.Arrays;
import java.util.HashMap;

public class input {
	private long window;
	
	private boolean keys[];
	
	
	// we use hashmap for keys, that were pressed since the game start
	// by default it is empty since we pressed nothing
	// as we generate input -- this map will grow 
	//final private HashMap<Integer, Boolean> trackedKeys = new HashMap<>();
	
	public input(long window)
	{
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		//Arrays.fill(keys, Boolean.FALSE);
		
		for(int iNo = 32; iNo < GLFW_KEY_LAST; iNo++)
		{
			keys[iNo] = false;
		}
	}	
	
	public boolean isKeyDown(int key)
	{
		return glfwGetKey(window, key) == 1;
	}
	
	public boolean isKeyPressed(int key)
	{
		// we use java8 HashMap.putIfAbsent() method, to put new value there
		// we store false value here to allow everyone during this tick also catch this key
		// if we want to process the key only once during the tick -- put true to map
		// putIfAbsent will return current value (boxed) or null, so we have to compare it with strictly boxed TRUE to prevent NPE
		// so we are good, if we got null or False in our map (not TRUE)
		// see java doc for this method to tweak this by your needs (disallow extra processing during tick, etc...)
		//return isKeyDown(key) && !(trackedKeys.putIfAbsent(key, false) == Boolean.TRUE);
		
		return (isKeyDown(key) && !keys[key]);
	}
	
	public boolean isKeyReleased(int key)
	{
		return (!isKeyDown(key) && keys[key]);
	}
	
	public boolean isMouseButtonDown(int button)
	{
		return glfwGetMouseButton(window, button) == 1;
	}
	
	public void update() 
	{
		// Update all keys we have in map with its current state
        //trackedKeys.forEach((k, v) -> trackedKeys.put(k, isKeyDown(k)));
        
		
		for(int iNo = 32; iNo < GLFW_KEY_LAST; iNo++)
		{
			keys[iNo] = isKeyDown(iNo);
		}
		
	}
}
