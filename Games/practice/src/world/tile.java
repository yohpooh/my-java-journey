package world;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class tile {
	public static tile tiles[] = new tile[16];
	
	public static final tile test_tile = new tile((byte) 0, "texture");
	
	private byte id;
	private String texture;
	
	public tile(byte id, String texture)
	{ 
		this.id = id;
		this.texture = texture;
		if(tiles[id] != null)
		{
			throw new IllegalStateException("Tiles at [" + id + "] is already being used!");
		}
		tiles[id] = this; 
	}

	public byte getId() {
		return id;
	}

	public String getTexture() {
		return texture;
	}
	
}
    