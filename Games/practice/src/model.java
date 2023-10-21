import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class model {

	private int draw_count;
	private int v_id;
	private int t_id;
	
	private int i_id;
	
	public model(float[] vertices, float[] tex_coords, int[] indices)
	{
		draw_count = indices.length;
		
		
		
		v_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
		
		t_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords), GL_STATIC_DRAW);
		
		
		i_id = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBufferInt(indices), GL_STATIC_DRAW);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void render()
	{
		//glEnableClientState(GL_VERTEX_ARRAY); 
		//glEnableClientState(GL_TEXTURE_COORD_ARRAY); 
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		//glVertexPointer(3, GL_FLOAT, 0, 0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		//glTexCoordPointer(2, GL_FLOAT, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		glDrawElements(GL_TRIANGLES, draw_count, GL_UNSIGNED_INT, 0);
		
		//unbind
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		//glDrawArrays(GL_TRIANGLES, 0, draw_count);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		//glDisableClientState(GL_VERTEX_ARRAY);
		//glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}
	
	private FloatBuffer createBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private IntBuffer createBufferInt(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
