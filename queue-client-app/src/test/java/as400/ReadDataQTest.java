package as400;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReadDataQTest {

	private static String propertyFile = "src/test/resources/as400Queue.properties";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadQueue() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)  {
	
		ReadDataQ readDataQ = new ReadDataQ();
		readDataQ.loadPropertyFile(propertyFile);
		try {
			readDataQ.readQueue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
