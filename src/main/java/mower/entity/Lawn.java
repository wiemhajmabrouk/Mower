package mower.entity;

import java.util.ArrayList;
import java.util.List;


public class Lawn {

	final private LawnSize lawnSize;

	private ArrayList<Mower> mowerList = new ArrayList<Mower>();
	private int remainingMovesNbr = 0;

	public Lawn(int eastWestSizeOnX, int northSouthSizeOnX) {
		this.lawnSize = new LawnSize(eastWestSizeOnX, northSouthSizeOnX);
	}

	public LawnSize getLawnSize() {
		return lawnSize;
	}

	public Mower addMower(int longitude, int latitude, char orientation, String movesList) {
		Mower tondeuse = new Mower(longitude, latitude, orientation, this.lawnSize, movesList);
		mowerList.add(tondeuse);

		int movesNumber = movesList.length();
		remainingMovesNbr = Math.max(remainingMovesNbr,
				movesNumber);

		return tondeuse;
	}

	public void executeNextMoveForMower() {
		for (Mower mower : mowerList) {
			mower.executeNextMove();
			remainingMovesNbr--;
		}
	}

	public void executeRemainingMoves() {
		for (int i = remainingMovesNbr; i > 0; i--) {
			executeNextMoveForMower();
		}

	}

	public List<String> getAllMowerPositions() {
		ArrayList<String> positionsList = new ArrayList<String>();
		String position;
		int longitude;
		int latitude;
		char orientation;
		
		for (Mower mower: mowerList) {
			
			longitude = mower.getLongitude();
			latitude = mower.getLatitude();
			orientation = mower.getOrientation();

			position = longitude + " " + latitude + " " + orientation;
			positionsList.add(position);

		}
		
		return positionsList;
	}

}
