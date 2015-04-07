package edu.iastate.cs228.hw4;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;
public class project4JunitTests {
	
	ConvexHull ch;

	@Before
    public void setUp() throws InputMismatchException, FileNotFoundException {
		ch = new ConvexHull("test.txt");	//(5, 10)   (-10, -5)   (-5, 0)   (10, 10)   (-10, -10)   
		ch.lowestPoint();
    }
	@Test
	public void compareTest1()
	{	 
		assertEquals(-1, ch.pc.compare(ch.lowestPoint, ch.points[0]));
	}
	@Test
	public void compareTest2()
	{
		assertEquals(0, ch.pc.compare(ch.lowestPoint, ch.points[4]));
	}
	@Test
	public void compareTest3()
	{
		assertEquals(1, ch.pc.compare(ch.points[0], ch.points[1]));
	}

}
