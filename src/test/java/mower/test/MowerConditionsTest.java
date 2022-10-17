package mower.test;

import static org.junit.Assert.*;

import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mower.entity.Lawn;
import mower.entity.LawnSize;
import mower.entity.Mower;

import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MowerConditionsTest {

	@Test
	@Parameters({"5, 6"})
	public void createLawn(int eastWestSizeOnX, int northSouthOnY) {
		
		Lawn pelouse = new Lawn(eastWestSizeOnX, northSouthOnY);
		LawnSize lawnSize = pelouse.getLawnSize();
		assertEquals(eastWestSizeOnX, lawnSize.getEastWestSizeOnX());
		assertEquals(northSouthOnY, lawnSize.getNorthSouthSizeOnX());
		
	}
	
	@Test
	@Parameters({"1, 2, N"})
	public void createMower(int longitude, int latitude, char orientation){
		
		LawnSize lawnSize = new LawnSize(5, 5);
		Mower mower = new Mower(longitude, latitude, orientation, lawnSize, "");
		assertEquals(longitude, mower.getLongitude());
		assertEquals(latitude, mower.getLatitude());
		assertEquals(orientation, mower.getOrientation());
		
	}
	
	@Test
	@Parameters({"1, 2, N"})
	public void createMowerInLawn(int longitude, int latitude, char orientation) {
		
		Lawn pelouse = new Lawn(5, 5);
		pelouse.addMower(longitude, latitude, orientation, "");
		
	}

}
