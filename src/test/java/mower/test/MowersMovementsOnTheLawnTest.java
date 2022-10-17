package mower.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mower.entity.Lawn;
import mower.entity.LawnSize;
import mower.entity.Mower;

@RunWith(JUnitParamsRunner.class)
public class MowersMovementsOnTheLawnTest {

	@Test
	@Parameters({ "1, 2, N, GAGAGAGAA, 1, 3, N" })
	public void moveTheMower(int longitude,
			int latitude, char orientation, String movesList, int expectedLongitude, int expectedLatitude,
			char expectedOrientation) {

		LawnSize lawnSize = new LawnSize(5, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, movesList);
		for (int i = 0; i < movesList.length(); i++) {
			mower.executeNextMove();
		}
		assertEquals(1, mower.getLongitude());
		assertEquals(3, mower.getLatitude());
		assertEquals('N', mower.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, GAGAGAGAA, 1, 3, N" })
	public void ignoreOrderedMoves(int longitude, int latitude,
			char orientation, String movesList, int expectedLongitude, int expectedLatitude,
			char expectedOrientation) {

		LawnSize lawnSize = new LawnSize(5, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, movesList);
		for (int i = 0; i <= movesList.length(); i++) {
			mower.executeNextMove();
		}
		assertEquals(expectedLongitude, mower.getLongitude());
		assertEquals(expectedLatitude, mower.getLatitude());
		assertEquals(expectedOrientation, mower.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, GAGAGAGAA, 1, 3, N" })
	public void ecuteMowerMoves(int longitude,
			int latitude, char orientation, String movesList, int expectedLongitude, int expectedLatitude,
			char expectedOrientation) {

		Lawn lawn = new Lawn(5, 5);
		Mower mower = lawn.addMower(longitude, latitude, orientation, movesList);
		for (int i = 0; i <= movesList.length(); i++) {
			lawn.executeNextMoveForMower();
		}
		assertEquals(expectedLongitude, mower.getLongitude());
		assertEquals(expectedLatitude, mower.getLatitude());
		assertEquals(expectedOrientation, mower.getOrientation());

	}

	public Object mowerParams() {
		int[] longitude = { 1, 3 };
		int[] latitude = { 2, 3 };
		char[] orientation = { 'N', 'E' };
		String[] movesList = { "GAGAGAGAA", "AADAADADDA" };
		int[] expectedLongitude = { 1, 5 };
		int[] expectedLatitude = { 3, 1 };
		char[] expectedOrientation = { 'N', 'E' };
		return new Object[][] { { longitude, latitude, orientation, movesList, expectedLongitude,
				expectedLatitude, expectedOrientation } };
	}

	@Test
	@Parameters(method = "mowerParams")
	public void addMowersToTheLawn(int[] longitude, int[] latitude,
			char[] orientation, String[] movesList, int[] expectedLongitude, int[] expectedLatitude,
			char[] expectedOrientation) {

		int mowerNbr = longitude.length;
		ArrayList<Mower> mowerList = new ArrayList<Mower>();

		Lawn lawn = new Lawn(5, 5);

		for (int i = 0; i < mowerNbr; i++) {
			mowerList
					.add(lawn.addMower(longitude[i], latitude[i], orientation[i], movesList[i]));
		}

		lawn.executeRemainingMoves();

		for (int i = 0; i < mowerNbr; i++) {

			assertEquals(expectedLongitude[i], mowerList.get(i).getLongitude());
			assertEquals(expectedLatitude[i], mowerList.get(i).getLatitude());
			assertEquals(expectedOrientation[i], mowerList.get(i).getOrientation());
		}

	}

}
