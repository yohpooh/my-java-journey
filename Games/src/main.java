import static org.lwjgl.glfw.GLFW.*; //use to import glfw which use for creating window

import org.lwjgl.glfw.GLFWVidMode;
public class main {
	public static void main(String[] args) {
		if(!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long mainWindow = glfwCreateWindow(640, 480, "LGJGL", 0, 0); //creation of window
		if(mainWindow == 0) { // checks if the window is working
			throw new IllegalStateException("Failed to create window");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); 
		glfwSetWindowPos(mainWindow, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2); //center window 
		glfwShowWindow(mainWindow);//show the main window
		
		while(!glfwWindowShouldClose(mainWindow)) { //update window until it close
			glfwPollEvents();
		}
		glfwTerminate(); // clean the whole glfw
	}
}
