package mower.fileReader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import mower.entity.Lawn;
import mower.enumeration.DirectionValuesEnum;

import java.io.FileReader;
import java.io.IOException;

public class InputFileReader {

	private ArrayList<String> linesList = new ArrayList<String>();
	private Lawn lawn;

	public InputFileReader(String absolutePath) throws IOException {

		BufferedReader bufferReader = new BufferedReader(new FileReader(absolutePath));

		String newLigne = new String();

		newLigne = bufferReader.readLine();
		String[] size = newLigne.split(" ");
		int eastWestSizeOnX = Integer.parseInt(size[0]);
		int northSouthSizeOnY = Integer.parseInt(size[1]);
		lawn = new Lawn(eastWestSizeOnX, northSouthSizeOnY);

		linesList.add(newLigne);

		String[] startPosition;
		int longitude;
		int latitude;
		char orientation;
		String movesList;

		while (bufferReader.ready()) {
			newLigne = bufferReader.readLine();
			linesList.add(newLigne);
			startPosition = newLigne.split(" ");

			longitude = Integer.valueOf(startPosition[0]);
			latitude = Integer.valueOf(startPosition[1]);
			orientation = startPosition[2].charAt(0);

			movesList = bufferReader.readLine();
			linesList.add(movesList);

			lawn.addMower(longitude, latitude, orientation, movesList);
		}
		bufferReader.close();

	}

	public List<String> getFileLines() {
		return linesList;
		}

	 public List<String> getAllMowerPositions() {
		 return lawn.getAllMowerPositions();
	 }

	public Lawn getLawn() {
		return lawn;
	}

	public void executeMowersMoves() {
		lawn.executeRemainingMoves();
	}

}
