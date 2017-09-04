package as400;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WriteDataQTest {

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
	public void testWriteQueue() {
		
		
		fail("Not yet implemented");
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)  {
	
		WriteDataQ writeDataQ = new WriteDataQ();
		writeDataQ.loadPropertyFile(propertyFile);
		writeDataQ.writeQueue();
		
	}
}
