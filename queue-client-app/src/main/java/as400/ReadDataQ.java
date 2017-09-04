package as400;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.DataQueue;
import com.ibm.as400.access.DataQueueEntry;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IllegalObjectTypeException;
import com.ibm.as400.access.ObjectDoesNotExistException;


import exception.MessageException;

public class ReadDataQ extends AS400Settings {
	
	private String targetSystem = "";
	private String targetUserId = "";
	private String targetUserPassword = "";	
	private String targetQueue = "";
	private AS400 system = null;
	
	public ReadDataQ(){		
	}
	
	//please look at https://stackoverflow.com/questions/16317554/jtopen-keyeddataqueue-read-timeout for more info

	/*
	 *  -1 = forever
	 *  1 = wait up to 1 second for a value then return null if nothing is found
	 */
	private int waitSeconds = -1;
	
	public int getWaitSecs(){
		return waitSeconds;
	}
	
	public void setWaitSecs(int waitSeconds){
		this.waitSeconds = waitSeconds;
	}
	
	
	public void loadPropertyFile(String propertyFile)
	{
		Properties properties = new Properties();
		try 
		{	
			System.out.println(propertyFile);			
			properties.load(WriteDataQ.class.getClassLoader().getResourceAsStream(propertyFile));
			if (properties != null)
			{
				targetSystem = properties.getProperty("local.system").trim();
				targetUserId =  properties.getProperty("local.user.id").trim();
				targetUserPassword =  properties.getProperty("local.user.password").trim();				
				targetQueue = properties.getProperty("destination.queue").trim();
				system =  new AS400(targetSystem,targetUserId,targetUserPassword);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	public String readQueue() throws Exception {
		
		String result = "";
		
		System.out.println("Reading DataQ " + targetQueue + " with waitSeconds " + waitSeconds);
		
		try
		{			
			
			//Read the DataQueue entry
			DataQueueEntry dataQEntry = new DataQueue(system, targetQueue).read(waitSeconds);
			system.connectService(AS400.DATAQUEUE);
			
			System.out.println("dataQEntry !=null ? " + dataQEntry != null);
			
			//Load the result
			if (dataQEntry != null)
			{
				result = dataQEntry.getString().trim();
				System.out.println("dataQEntry result " + result);
			}
		}
		catch (AS400SecurityException ase)
		{
			throw new MessageException(ase.getMessage().toString(),ase);
		}
		catch (IOException ioe)
		{
			throw new MessageException(ioe.getMessage().toString(),ioe);
		}
		catch (ErrorCompletingRequestException ecre)
		{
			throw new MessageException(ecre.getMessage().toString(),ecre);
		}
		catch (IllegalObjectTypeException iote)
		{
			throw new MessageException(iote.getMessage().toString(),iote);
		}
		catch (InterruptedException ie)
		{
			throw new MessageException(ie.getMessage().toString(),ie);
		}
		catch (ObjectDoesNotExistException odne)
		{
			throw new MessageException(odne.getMessage().toString(),odne);
		}
		catch (RuntimeException re)
		{
			throw new MessageException(re.getMessage().toString(),re);
		}
		finally
		{
			//Disconnect DataQueue service
			if (system !=null)
			{
				system.disconnectService(AS400.DATAQUEUE);
			}			
		}
		
		return result;			
	}
	
	


	
}
