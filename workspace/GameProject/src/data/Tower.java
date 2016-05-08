package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;
import static helpers.Clock.delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Tower implements Entity {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range;
	public Enemy target;
	private Texture[] textures;
	private CopyOnWriteArrayList<Enemy> enemies;
	private boolean targeted;
	public ArrayList<Projectile> projectiles;
	public TowerType type;

	public Tower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		this.textures = type.textures;
		this.type = type;
		//this.damage = type.damage;
		this.range = type.range;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.enemies = enemies;
		this.targeted = false;
		this.timeSinceLastShot = 0f;
		this.projectiles = new ArrayList<Projectile>();
		this.firingSpeed = type.firingSpeed;
		this.angle = 0f;

	}

	private Enemy acquireTarget() {
		Enemy closet = null;
		//random distance to help sorting enemy distance
		float closetDistance = 10000;
		//return closet enemy
		for (Enemy e : enemies) {
			if (isInRange(e) && findDistance(e) < closetDistance && e.isAlive()) {
				closetDistance = findDistance(e);
				closet = e;
			}
		}
		//if enemy exist and returned, target = true
		if (closet != null) {
			targeted = true;
		}
		return closet;
	}

	private boolean isInRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}

	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}

	//abstract cause tower have different style of shoot
	public abstract void shoot(Enemy target);

	public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList) {
		enemies = newList;
	}

	public void update() {
		if (!targeted) {
			target = acquireTarget();
		} else {
			angle = calculateAngle();
			if (timeSinceLastShot > firingSpeed) {
				shoot(target);
				timeSinceLastShot = 0;
			}
		}

		if (target == null || target.isAlive() == false) {
			targeted = false;
		}

		timeSinceLastShot += delta();

		for (Projectile p : projectiles) {
			p.update();
		}

		draw();
	}

	public void draw() {
		// TODO Auto-generated method stub
		drawQuadTex(textures[0], x, y, width, height);
		if (textures.length > 1) {
			for (int i = 1; i < textures.length; i++)
				drawQuadTexRotate(textures[i], x, y, width, height, angle);
		}
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

	public Enemy getTarget() {
		return target;
	}

}
