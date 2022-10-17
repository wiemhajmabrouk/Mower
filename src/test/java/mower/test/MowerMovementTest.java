package mower.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mower.entity.LawnSize;
import mower.entity.Mower;

@RunWith(JUnitParamsRunner.class)
public class MowerMovementTest {

	@Test
	@Parameters({ "1, 2, N, 1, 3", "1, 2, S, 1, 1", "1, 2, E, 2, 2", "1, 2, W, 0, 2" })
	public void forwardTheMower(int longitude, int latitude, char orientation, int expectedLongitude,
			int expectedLatitude) {

		LawnSize lawnSize = new LawnSize(5, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, "");
		mower.forward();
		assertEquals(expectedLongitude, mower.getLongitude());
		assertEquals(expectedLatitude, mower.getLatitude());

	}

	@Test
	@Parameters({ "6, 5, N, 6, 5", "0, 0, S, 0, 0", "6, 5, E, 6, 5", "0, 0, W, 0, 0" })
	public void forwardMowerFurtherThanTheBorderOfTheLawn(int longitude, int latitude, char orientation,
			int expectedLongitude, int expectedLatitude) {

		LawnSize lawnSize = new LawnSize(6, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, "");
		mower.forward();
		assertEquals(expectedLongitude, mower.getLongitude());
		assertEquals(expectedLatitude, mower.getLatitude());

	}

	@Test
	@Parameters({ "1, 2, N, E", "1, 2, S, W", "1, 2, E, S", "1, 2, W, N", })
	public void mowerToTheRight(int longitude, int latitude, char orientation,
			char expectedOrientation) {

		LawnSize lawnSize = new LawnSize(6, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, "");
		mower.toTheRight();
		assertEquals(expectedOrientation, mower.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, W", "1, 2, S, E", "1, 2, E, N", "1, 2, W, S", })
	public void mowerToTheLeft(int longitude, int latitude, char orientation,
			char expectedOrientation) {

		LawnSize lawnSize = new LawnSize(6, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, "");
		mower.toTheLeft();
		assertEquals(expectedOrientation, mower.getOrientation());

	}

	@Test
	@Parameters({ "1, 2, N, 2, 3, S" })
	public void moveAndTurnTheMower(int longitude, int latitude, char orientation,
			int expectedLongitude, int expectedLatitude, char expectedOrientation) {

		LawnSize lawnSize = new LawnSize(6, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, "");
		mower.forward();
		mower.toTheRight();
		mower.forward();
		mower.toTheRight();
		assertEquals(expectedLongitude, mower.getLongitude());
		assertEquals(expectedLatitude, mower.getLatitude());
		assertEquals(expectedOrientation, mower.getOrientation());

	}

}
