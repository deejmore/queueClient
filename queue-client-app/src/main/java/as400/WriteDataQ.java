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
	private String targetSystem = "local";
	private String userId = "username";
	private String userPassword = "password";
	private String commandToRun = "ADDLIBLE LIB(MNICHOLSON)";
	private String destinationQ = "/QSYS.LIB/MNICHOLSON.LIB/MARCODQ.DTAQ";
	private String messageToQ = "hello frm dataq land on ";
	
	public WriteDataQ()
	{							
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
				userId =  properties.getProperty("local.user.id").trim();
				userPassword =  properties.getProperty("local.user.password").trim();
				commandToRun =  properties.getProperty("command.to.run").trim();
				destinationQ =  properties.getProperty("destination.queue").trim();
				messageToQ =  properties.getProperty("message.to.queue").trim();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	public AS400 getAS400Connection() {
		return new AS400(targetSystem,userId,userPassword);
	}


	public String writeQueue(){
		
		String result = "";
			
		try { 
			
			AS400 system = getAS400Connection() ;
			CommandCall command = new CommandCall(system);
			command.run(commandToRun); 
			DataQueue output_dataQ = new DataQueue(system, destinationQ);
			
			output_dataQ.write(messageToQ);
			//output_dataQ.write(messageToQ.concat(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
			
			result = "written to dataq OK";
		
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


	public String getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCommandToRun() {
		return commandToRun;
	}

	public void setCommandToRun(String commandToRun) {
		this.commandToRun = commandToRun;
	}

	public String getDestinationQ() {
		return destinationQ;
	}

	public void setDestinationQ(String destinationQ) {
		this.destinationQ = destinationQ;
	}

	public String getMessageToQ() {
		return messageToQ;
	}

	public void setMessageToQ(String messageToQ) {
		this.messageToQ = messageToQ;
	}
	
	
}
