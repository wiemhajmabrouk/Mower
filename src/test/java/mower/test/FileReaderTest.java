package mower.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mower.entity.Lawn;
import mower.entity.LawnSize;
import mower.fileReader.InputFileReader;

@RunWith(JUnitParamsRunner.class)
public class FileReaderTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private String fillTemporaryFile(List<String> input) throws IOException {

		String inputData = new String();
		int lignesNbr = input.size();
		for (String nextLigne: input) {
			lignesNbr--;
			inputData += nextLigne;
			if (lignesNbr > 0) {
				inputData += System.lineSeparator();
			}
		}
		
		final File tempFile = tempFolder.newFile("inputFile.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
		bufferedWriter.write(inputData);
		bufferedWriter.close();
		return tempFile.getAbsolutePath();

	}

	@Test
	@Parameters({ "5 5, 1 2 N, GAGAGAGAA, 3 3 E, AADAADADDA" })
	public void fileContentInFileReader(List<String> input) throws IOException {

		String path = fillTemporaryFile(input);
		InputFileReader fileSize = new InputFileReader(path);
		assertEquals(input, fileSize.getFileLines());
	}

	public Object createLawnAndMowersAndMakeMoves() {

		String[] input = { "5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA" };
		int eastWestSizeOnX = 5;
		int northSouthOnY = 5;
		String[] positions = { "1 3 N", "5 1 E" };
		return new Object[][] { { input, eastWestSizeOnX,
				northSouthOnY, positions } };
	}

	@Test
	@Parameters(method = "createLawnAndMowersAndMakeMoves")
	public void ReadInputFileAndCreateLawnAndMowers(
			List<String> input, int eastWestSizeOnX,
			int northSouthOnY, String[] positions) throws IOException {

		String path = fillTemporaryFile(input);

		InputFileReader fileSize = new InputFileReader(path);
		Lawn lawn = fileSize.getLawn();
		LawnSize lawnSize = lawn.getLawnSize();
		assertEquals(eastWestSizeOnX, lawnSize.getEastWestSizeOnX());
		assertEquals(northSouthOnY, lawnSize.getNorthSouthSizeOnX());

		fileSize.executeMowersMoves();;

		List<String> mowerPositions = fileSize.getAllMowerPositions();
		assertEquals(positions, mowerPositions);
	}

}
