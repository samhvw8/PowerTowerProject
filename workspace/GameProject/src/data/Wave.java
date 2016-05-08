package data;

import java.util.concurrent.CopyOnWriteArrayList;

import static helpers.Clock.*;
import static helpers.Artist.*;

public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private CopyOnWriteArrayList<Enemy> enemyList;
	private int enemiesPerWave, enemiesSpawned;
	private boolean waveCompleted;
	
	public Wave( Enemy enemyType,float spawnTime, int enemiesPerWave){
		this.enemiesPerWave = enemiesPerWave;
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		this.enemiesSpawned = 0;
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		this.waveCompleted = false;
		
		spawn();
	}
	
	public void update(){
		//assume all enemy are dead until the loop
		boolean allEnemiesDead = true;
		if (enemiesSpawned < enemiesPerWave) {
			timeSinceLastSpawn += delta();
			if (timeSinceLastSpawn > spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}
		for(Enemy e : enemyList){
			if(e.isAlive()){
				allEnemiesDead = false;
				e.update();
				e.draw();
			} else{
				enemyList.remove(e);
			}
		}
		if(allEnemiesDead){
			waveCompleted = true;
		}
	}
	
	private void spawn(){
		enemyList.add(new Enemy(enemyType.getTexture(),
				enemyType.getStartTile(),
				enemyType.getTileGrid(),TILE_SIZE,TILE_SIZE,enemyType.getSpeed(),
				enemyType.getHealth()));
		enemiesSpawned++;
	}
	
	public boolean isCompleted(){
		return waveCompleted;
	}
	
	public CopyOnWriteArrayList<Enemy> getEnemyList(){
		return enemyList;
	}
}
