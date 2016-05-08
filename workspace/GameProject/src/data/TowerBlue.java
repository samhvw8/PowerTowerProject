package data;

import java.util.concurrent.CopyOnWriteArrayList;


public class TowerBlue extends Tower{
	
	public TowerBlue(TowerType type, Tile startTile,CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile,enemies);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void shoot(Enemy target){
		super.projectiles.add(new ProjectileCannon(super.type.projectileType,super.target,super.getX(),super.getY(),32,32));
	}
}
