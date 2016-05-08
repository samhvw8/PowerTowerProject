package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import static helpers.Artist.*;

public abstract class Projectile implements Entity{

	private Texture texture;
	private float x, y , speed, xVelocity, yVelocity;
	private int damage,width, height;
	private Enemy target;
	private boolean alive;

	public Projectile(ProjectileType type, Enemy target,float x,float y,int width, int height) {
		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();
	}

	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		
		//set direction base on position of target relative to tower
		if (target.getX() < x) {
			xVelocity *= -1;
		}
		if (target.getY() < y) {
			yVelocity *= -1;
		}
	}
	
	//deal damage to enemy
	public void damage(){
		target.damage(damage);
		alive = false;
	}

	public void update() {
		if (alive) {
			x += xVelocity * speed * delta();
			y += yVelocity * speed * delta();
			if (checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(),
					target.getHeight())) {
				damage();
			}
			draw();
		}
	}

	public void draw() {
		drawQuadTex(texture, x, y, 32, 32);
	}

	
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	
	public void setX(float x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	
	public void setY(float y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
	}

	
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
	}
	
	public Enemy getTarget(){
		return target;
	}
	
	public void setAlive(boolean status){
		alive = status;
	}
}
