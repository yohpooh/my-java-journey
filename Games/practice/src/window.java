import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

public class window {
	private long window;
	
	private int width, height;
	private boolean fullscreen;
	
	private input input;
	
	public static void setStaticCallback()
	{
		glfwSetErrorCallback(new GLFWErrorCallback()
				{
					@Override
					public void invoke(int error, long description) {
						// TODO Auto-generated method stub
						throw new IllegalStateException(GLFWErrorCallback.getDescription(description));					
						}
				});
	}
	
	public window()
	{
		setSize(640,480);
		setFullscreen(false);
	}
	
	public void createWindow(String title)
	{
		window = glfwCreateWindow(
				width, 
				height, 
				title, 
				fullscreen ? glfwGetPrimaryMonitor() : 0, 
				0);
		if(window == 0)
		{
			throw new IllegalStateException("Failed to create window!");
		}
		
		if(!fullscreen)
		{
			GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); 
			glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - width) / 2); //center window 
			
		}

		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		
		input = new input(window);
	}
	
	public boolean shouldClose()
	{
		return !glfwWindowShouldClose(window);
	}
	
	public void setFullscreen(boolean fullscreen)
	{
		this.fullscreen = fullscreen;
	}
	
	public void update()
	{
		input.update();
		glfwPollEvents();
	}
	
	public void setSize(int width, int height) {
		this.height = height;
		this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isFullscreen()
	{
		return fullscreen;
	}
	
	public void swapBuffers()
	{
		glfwSwapBuffers(window);
	}
	
	public long getWindow()
	{
		return window;
	}
	
	public input getInput() 
	{
		return input;
	}
	
}
