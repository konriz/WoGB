package objects;

import com.badlogic.gdx.graphics.Texture;


// TODO projectile class!
public abstract class Projectile {
	
	private Texture image;
	private int[] pos;
	private float speed = 1f;
	private boolean visible;
	
	public Projectile(Texture image)
	{
		this.image = image;
	}
	
	public int[] getPos() {
		return this.pos;
	}

	public Texture getImage()
	{
		return this.image;
	}
	
	public boolean isVisible()
	{
		return this.visible;
	}
	
	public void shoot(int[] shooter, int[] target)
	{
		this.pos = shooter;
		
	}
	
}
