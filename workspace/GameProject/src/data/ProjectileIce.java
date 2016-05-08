package data;

public class ProjectileIce extends Projectile {

	public ProjectileIce(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void damage() {
		super.getTarget().setSpeed(4);
		super.damage();
	}

}
