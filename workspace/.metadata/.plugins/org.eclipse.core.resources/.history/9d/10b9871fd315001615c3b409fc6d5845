package data;

import static helpers.Artist.*;

public class TileGrid {
	
	private int tileWidth,tileHeight;
	private Tile[][] map;
	
	public TileGrid(){
		this.tileWidth = 15;
		this.tileHeight = 10;
		map = new Tile[tileWidth][tileHeight];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE,TILE_SIZE, TileType.Grass);
			}
		}
	}
	
	public TileGrid(int[][] newMap){
		this.tileWidth = newMap[0].length;
		this.tileHeight = newMap.length;
		map = new Tile[tileWidth][tileHeight];
		for(int i = 0; i< map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				
				switch(newMap[j][i]){
				case 0:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE,TILE_SIZE, TileType.Grass);
					break;
				case 1:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE,TILE_SIZE, TileType.Moutain);
					break;
				case 2:
					map[i][j] = new Tile(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE,TILE_SIZE, TileType.Water);
					break;
				}
			}
		}
	}
	
	public void setTile(int xCoord, int yCoord, TileType type){
		map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE, type);
	}
	
	public Tile getTile(int xPlace, int yPlace){
		if(xPlace < tileWidth && yPlace < tileHeight && xPlace > -1 && yPlace > -1)
			return map[xPlace][yPlace];
		else
			return new Tile(0, 0 ,0 ,0 ,TileType.NULL);
	}
	
	public void draw(){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j].draw();
			}
		}
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
}
