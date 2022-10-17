package mower;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mower.fileReader.InputFileReader;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws IOException
    {
    	File inputFile = new File("inputFiles/inputFile.txt");
    	
    	InputFileReader reader = new InputFileReader(inputFile.getAbsolutePath());
    	reader.executeMowersMoves();
    	List<String> resultList = reader.getAllMowerPositions();
    	for (String result: resultList) {
			System.out.println(result);
		}
		
    }
	
}
