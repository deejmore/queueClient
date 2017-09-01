package as400;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.DataQueue;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IllegalObjectTypeException;
import com.ibm.as400.access.ObjectDoesNotExistException;

public class WriteDataQ {

	private String dataQueue = "/QSYS.LIB/MNICHOLSON.LIB/MARCODQ.DTAQ";
	private String libraryCommand = "ADDLIBLE LIB(MNICHOLSON";
	private String dataQueueMessage = "hello frm dataq land on ";
	private String dataQueueResult = "written to dataq ok";
	public String writeQueue(){
		
		String result = "nothing to see";

	
		try { 
			Properties properties = new Properties();
			properties.load(WriteDataQ.class.getResourceAsStream("as400.properties")); 
			
			AS400 system = new AS400(properties.getProperty("local_system").trim(),properties.getProperty("userId").trim(),properties.getProperty("password").trim());
			
			CommandCall command = new CommandCall(system);
			command.run(libraryCommand); 
			DataQueue output_dataQ = new DataQueue(system, dataQueue);
			
			output_dataQ.write(dataQueueMessage.concat(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
			
			result = dataQueueResult;
		
		} catch (AS400SecurityException e) {
			result = e.getMessage().toString();
			e.printStackTrace();
		} catch (IOException e) {
			result = e.getMessage().toString();
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			result = e.getMessage().toString();
			e.printStackTrace();
		} catch (IllegalObjectTypeException e) {
			result = e.getMessage().toString();
			e.printStackTrace();
		} catch (InterruptedException e) {
			result = e.getMessage().toString();
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			result = e.getMessage().toString();
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
