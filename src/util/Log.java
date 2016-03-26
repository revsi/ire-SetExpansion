package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.xml.ws.spi.Invoker;

public class Log {
	public final static Logger log = Logger.getLogger(Invoker.class.getName()); 


	static {
		log.setLevel(Level.INFO);
		FileHandler temp = null;
		try {
			temp = new FileHandler("SetExpansion.log");    // File for storing the logs
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		temp.setFormatter(new SimpleFormatter());
		log.addHandler(temp);
	}
	
	public void writeLog(){
		
	}
	public Log() {
		// TODO Auto-generated constructor stub
	}

}
