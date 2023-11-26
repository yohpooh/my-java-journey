package world;

import java.util.HashMap;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import render.*;

public class tileRenderer {
	private HashMap<String, texture> tile_texture;
	private model model;
	
	public tileRenderer()
	{
		tile_texture = new HashMap<String, texture>();
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

		model = new model(vertices, texture, indices);
		
		for(int iNo = 0; iNo < tile.tiles.length; iNo++)
		{
			if(tile.tiles[iNo] != null)
			{				
				if(!tile_texture.containsKey(tile.tiles[iNo].getTexture()));
				{
					String tex = tile.tiles[iNo].getTexture();
					tile_texture.put(tex, new texture(tex + ".png"));
				}
			}
		}
	}
	
	public void renderTile(byte id, int x, int y, shader shade, Matrix4f world, camera cam)
	{
		shade.bind();
		if(tile_texture.containsKey(tile.tiles[id].getTexture()))
		{
			tile_texture.get(tile.tiles[id].getTexture()).bind(0);;
		}
		Matrix4f tile_pos = new Matrix4f().translate(new Vector3f(x * 2, y * 2, 0));
		Matrix4f target = new Matrix4f();
		
		cam.getProjection().mul(world, target);
		target.mul(tile_pos);
		
		shade.setUniform("sampler", 0);
		shade.setUniform("projection", target);
		
		model.render();
	}
}
