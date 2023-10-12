import static org.lwjgl.glfw.GLFW.*; //use to import glfw which use for creating window
import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.opengl.GL11.*;


public class main {
	public main()
	{
		if(!glfwInit()) {
			System.err.println("Failed to initialize GLFW!");
			System.exit(1);
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long mainWindow = glfwCreateWindow(640, 480, "Game 2D Practice", 0, 0); //creation of window
		if(mainWindow == 0) { // checks if the window is working
			throw new IllegalStateException("Failed to create window");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); 
		glfwSetWindowPos(mainWindow, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2); //center window 
		glfwShowWindow(mainWindow);//show the main window
		
		glfwMakeContextCurrent(mainWindow); //to make something on window
		GL.createCapabilities();
		glEnable(GL_TEXTURE_2D);
		texture tex = new texture("./res/texture.png");
		
		glClearColor(255, 255, 255, 0); //change the color to white using rgba
		int colorRed = 0;
		int colorBlue = 0;
		int colorGreen = 0;
		
		while(!glfwWindowShouldClose(mainWindow)) { //update window until it close
			glfwPollEvents();
			if(glfwGetKey(mainWindow, GLFW_KEY_SPACE) == GL_TRUE)
			{
				colorRed = 1;
				colorBlue = 0;
				colorGreen = 0;
				glfwSetWindowShouldClose(mainWindow, GL_TRUE);
			}
			else 
			{
				colorRed = 0;
				colorBlue = 0;
				colorGreen = 0;
			}
			
			glClear(GL_COLOR_BUFFER_BIT); //make the window color black and clear everything to black
			tex.bind();
			glBegin(GL_QUADS); //insert a square
				glTexCoord2f(0,0);
				glVertex2f(-0.5f, 0.5f);
				glTexCoord2f(1,0);
				glVertex2f(0.5f, 0.5f);
				glTexCoord2f(1,1);
				glVertex2f(0.5f, -0.5f);
				glTexCoord2f(0,1);
				glVertex2f(-0.5f, -0.5f);

				//glColor4f(colorRed, colorGreen, colorBlue, 0);
			glEnd();
			
			glfwSwapBuffers(mainWindow); //buffers this is essential to to make everything on windows to render
		}
		glfwTerminate(); // clean the whole glfw
	}
	
	private void glfwSetWindowShouldClose(long mainWindow, int glTrue) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new main();
	}
	
}
