package mower.entity;

public class LawnSize {

	private final int eastWestSizeOnX;
	private final int northSouthSizeOnX;

	public LawnSize(int eastWestSizeOnX, int northSouthSizeOnX) {
		this.eastWestSizeOnX = eastWestSizeOnX;
		this.northSouthSizeOnX = northSouthSizeOnX;
	}

	public int getEastWestSizeOnX() {
		return eastWestSizeOnX;
	}

	public int getNorthSouthSizeOnX() {
		return northSouthSizeOnX;
	}

}
