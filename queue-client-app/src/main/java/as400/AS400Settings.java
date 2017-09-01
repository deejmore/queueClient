package as400;

import org.apache.commons.lang.StringUtils;

import com.ibm.as400.access.AS400;
import exception.MessageException;



public class AS400Settings {
	
	protected String host;
	protected String schema; 
	protected String user; 
	protected String password;
	

	public AS400 getAS400Connection() {
		return new AS400(host,user,password);
	}

	public AS400 getAS400Connection(String user, String password) {
		return new AS400(host,user,password);
	}
	
	
	public String callPcmlProgram() throws MessageException {
		return null;
	}
	 
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public static String padRight(String s, int n) {
		return StringUtils.rightPad(s, n, ' '); 
	}

	public static String padLeft(String s, int n) {
		return StringUtils.leftPad(s, n, ' ');  
	}
	
	public static String padLeft(String s, int n, char c) {
	    return StringUtils.leftPad(s, n, c);
	}
	
}
