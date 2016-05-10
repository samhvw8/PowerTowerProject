package data;

public class WaveManager {

    private float timeSinceLastWave, timebetweenEnemy;
    private int waveNumber, enemiesPerWave;
    private Enemy enemyType;
    private Wave currentWave;

    public WaveManager(Enemy enemyType, float timebetweenEnemy, int enemiesPerWave) {
        this.enemyType = enemyType;
        this.timebetweenEnemy = timebetweenEnemy;
        this.enemiesPerWave = enemiesPerWave;
        this.timeSinceLastWave = 0;
        this.waveNumber = 0;
        this.currentWave = null;
        newWave();
    }

    public void update() {
        if (!currentWave.isCompleted()) {
            currentWave.update();
        } else {
            newWave();
        }

    }

    private void newWave() {
        currentWave = new Wave(enemyType, timebetweenEnemy, enemiesPerWave);
        waveNumber++;
        System.out.println("Beginning Wave" + waveNumber);
    }

    public Wave getCurrentWave() {
        return currentWave;
    }
}
