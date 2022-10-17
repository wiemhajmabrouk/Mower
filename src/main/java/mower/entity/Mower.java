package mower.entity;

import mower.enumeration.DirectionValuesEnum;

public class Mower {

	final private LawnSize lawnSize;

	private int longitude;
	private int latitude;
	private char orientation;
	private String movementList;

	public Mower(int longitude, int latitude, char direction, LawnSize lawnSize,
			String movementList) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.orientation = direction;
		this.lawnSize = lawnSize;
		this.movementList = movementList;

	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public char getOrientation() {
		return orientation;
	}

	@Override
	public String toString() {
		return "Tondeuse [Taille de pelouse=" + lawnSize + ", longitude=" + longitude + ", latitude="
				+ latitude + ", orientation=" + orientation + ", List de mouvements =" + movementList + "]";
	}

	public void forward() {

		if (orientation == DirectionValuesEnum.NORD.getValue()) {

			if (latitude < lawnSize.getNorthSouthSizeOnX()) {
				latitude++;
			}
		} else if (orientation == DirectionValuesEnum.SUD.getValue()) {

			if (latitude > 0) {
				latitude--;
			}
		} else if (orientation == DirectionValuesEnum.EST.getValue()) {

			if (longitude < lawnSize.getEastWestSizeOnX()) {
				longitude++;
			}
		} else if (orientation == DirectionValuesEnum.OUEST.getValue()) {

			if (longitude > 0) {
				longitude--;
			}
		}

	}

	public void toTheRight() {

		if (orientation == DirectionValuesEnum.NORD.getValue()) {
			orientation = DirectionValuesEnum.EST.getValue();
		} else if (orientation == DirectionValuesEnum.SUD.getValue()) {
			orientation = DirectionValuesEnum.OUEST.getValue();
		} else if (orientation == DirectionValuesEnum.EST.getValue()) {
			orientation = DirectionValuesEnum.SUD.getValue();
		} else if (orientation == DirectionValuesEnum.OUEST.getValue()) {
			orientation = DirectionValuesEnum.NORD.getValue();
		}
	}

	public void toTheLeft() {

		if (orientation == DirectionValuesEnum.NORD.getValue()) {
			orientation = DirectionValuesEnum.OUEST.getValue();
		} else if (orientation == DirectionValuesEnum.SUD.getValue()) {
			orientation = DirectionValuesEnum.EST.getValue();
		} else if (orientation == DirectionValuesEnum.EST.getValue()) {
			orientation = DirectionValuesEnum.NORD.getValue();
		} else if (orientation == DirectionValuesEnum.OUEST.getValue()) {
			orientation = DirectionValuesEnum.SUD.getValue();
		}
	}

	public void executeNextMove() {
		if (!movementList.isEmpty()) {

			if (movementList.charAt(0) == DirectionValuesEnum.AVANT.getValue()) {
				this.forward();
			} else if (movementList.charAt(0) == DirectionValuesEnum.DROITE.getValue()) {
				this.toTheRight();
			} else if (movementList.charAt(0) == DirectionValuesEnum.GAUCHE.getValue()) {
				this.toTheLeft();
			}
			movementList = movementList.substring(1);
		}
	}

}
