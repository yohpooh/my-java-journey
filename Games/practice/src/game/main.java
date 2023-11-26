package game;
import static org.lwjgl.glfw.GLFW.*; //use to import glfw which use for creating window
import org.lwjgl.opengl.GL;
import org.lwjgl.system.macosx.LibSystem;
import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.opengl.GL11.*;

//io package
import io.*;
//render package
import render.*;
//world package
import world.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;


public class main {
	public main()
	{
		window.setStaticCallback();
		if(!glfwInit()) {
			System.err.println("Failed to initialize GLFW!");
			System.exit(1);
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		window mainWindow = new window();
		mainWindow.setSize(640, 480);
		mainWindow.setFullscreen(false);
		mainWindow.createWindow("Practice");
		
		/*
		long mainWindow = glfwCreateWindow(640, 480, "Game 2D Practice", 0, 0); //creation of window
		if(mainWindow == 0) { // checks if the window is working
			throw new IllegalStateException("Failed to create window");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); 
		glfwSetWindowPos(mainWindow, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2); //center window 
		glfwShowWindow(mainWindow);//show the main window
		
		glfwMakeContextCurrent(mainWindow); //to make something on window
		*/
		GL.createCapabilities();
		
		camera cam = new camera(mainWindow.getWidth(), mainWindow.getHeight());
		glEnable(GL_TEXTURE_2D);
		tileRenderer tiles = new tileRenderer();
		
		/*
		float[] vertices = new float[]
				{
						-0.5f, 0.5f, 0, //top left		0
						0.5f, 0.5f, 0,  //top right		1
						0.5f, -0.5f, 0, // bottom right 2
						-0.5f, -0.5f, 0, //bottom left	3
				};
		float[] texture = new float[]
				{
						0, 0,
						1, 0,
						1, 1,
						0, 1,
				};
		
		int[] indices = new int[]
				{
						0, 1, 2,
						2, 3, 0
				};
		
		model mod = new model(vertices, texture, indices);
		*/
		shader shade = new shader("shader");
		//texture tex = new texture("./res/texture.png");
		
		/*
		Matrix4f projection = new Matrix4f()
				.ortho2D(-640/2, 640/2, -480/2, 480/2)
				.scale(128);
		*/
		Matrix4f scale = new Matrix4f()
				.translate(new Vector3f(0, 0, 0)) //move the texture to the right
				.scale(16);
		Matrix4f target = new Matrix4f();
		
		cam.setPosition(new Vector3f(-100, 0, 0)); //put back the texture in the middle
		
		double frame_cap = 1.0 / 60.0; //1 second per 60 frames
		
		double frame_time = 0; //seconds
		int frames = 0; //how many frames passed
		
		double time = timer.getTime();
		double unprocessed = 0;
		//projection.mul(scale, target);
		
		//glClearColor(255, 255, 255, 0); //change the color to white using rgba
		glClearColor(0, 0, 0, 0); //change the color to black using rgba
		
		
		//int colorRed = 0;
		//int colorBlue = 0;
		//int colorGreen = 0;
		
		while(mainWindow.shouldClose() /*!glfwWindowShouldClose(mainWindow)*/) { //update window until it close
			boolean can_render = false;
			double time_2 = timer.getTime();
			double passed = time_2 - time;
			
			unprocessed += passed;
			frame_time += passed;
			
			time = time_2;
			
			while(unprocessed >= frame_cap)
			{				
				unprocessed-=frame_cap;
				can_render = true;
				
				target = scale;

					if(mainWindow.getInput().isKeyPressed(GLFW_KEY_ESCAPE))
					{
						//colorRed = 1;
						//colorBlue = 0;
						//colorGreen = 0;
						System.out.println("Pressed");
						glfwSetWindowShouldClose(mainWindow.getWindow(), GL_TRUE);
					}
					/*
					else 
					{
						//colorRed = 0;
						//colorBlue = 0;
						//colorGreen = 0;
					}
					*/
				
				mainWindow.update();
				if(frame_time >= 1.0)
				{
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			
			if(can_render)
			{
				//glClear(GL_COLOR_BUFFER_BIT); //make the window color black and clear everything to black
				//shade.bind();
				//shade.setUniform("sampler", 0);
				//shade.setUniform("projection", cam.getProjection().mul(target));
				//mod.render();
				//tex.bind(0);
				
				/*
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
				 */
				
				for(int iNo = 0; iNo < 8; iNo++)
				{
					tiles.renderTile((byte)0, iNo, 0, shade, scale, cam);
				}
				
				mainWindow.swapBuffers();
				//glfwSwapBuffers(mainWindow); //buffers this is essential to to make everything on windows to render		
				frames++;
			}
			
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
