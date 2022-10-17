/**
 * 
 */
package mower.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import mower.App;

import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class AppTest {

    private final static ByteArrayOutputStream output = new ByteArrayOutputStream();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
        System.setOut(new PrintStream(output, true, "UTF-8"));
	}

	@Test
	public void mainTest() throws IOException {
		App.main(null);
		String result = "1 3 N" + System.lineSeparator() + "5 1 E" + System.lineSeparator();
		assertEquals(result, output.toString());
	}
	
	@AfterClass
    public static void tearDownAfterClass() {
        System.setOut(System.out);
    }

}
