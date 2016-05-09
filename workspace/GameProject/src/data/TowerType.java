package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;
public enum TowerType {
	
	RedTower(new Texture[]{quickLoad("tower"),quickLoad("gun")},ProjectileType.CannonBall,10,1000,3,10),
	BlueTower(new Texture[]{quickLoad("bluetower"),quickLoad("bluegun")},ProjectileType.CannonBall,30,1000,3,25),
	IceTower(new Texture[]{quickLoad("tower"),quickLoad("bluegun")},ProjectileType.IceBall,30,1000,3,55);
	
	Texture[] textures;
	ProjectileType projectileType;
	int damage, range, cost;
	float firingSpeed;
	
	TowerType(Texture[] textures,ProjectileType projectileType, int damage, int range,float firingSpeed, int cost){
		this.textures = textures;
		this.damage = damage;
		this.range = range;
		this.projectileType = projectileType;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}
}
