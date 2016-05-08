package data;

public class CheckPoint {

	private Tile tile;
	private int xDriection,yDirection;
	
	public CheckPoint(Tile tile, int xDirection, int yDirection){
		this.tile = tile;
		this.xDriection = xDirection;
		this.yDirection = yDirection;
	}

	public Tile getTile() {
		return tile;
	}

	public int getxDriection() {
		return xDriection;
	}

	public int getyDirection() {
		return yDirection;
	}
	
}
