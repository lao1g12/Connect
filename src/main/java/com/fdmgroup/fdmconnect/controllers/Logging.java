package com.fdmgroup.fdmconnect.controllers;
import org.apache.log4j.Logger;

public class Logging {
	
	final static Logger logger = Logger.getLogger(Logging.class);
	
	public static void Log(String severity, String message){
		
		Logging obj = new Logging();
		
		if (severity.equals("fatal")){
			obj.logFatal(message);
		} else if (severity.equals("info")){
			obj.logInfo(message);
		} else if (severity.equals("error")){
			obj.logError(message);
		} else if (severity.equals("warning")){
			obj.logWarning(message);
		} else if (severity.equals("debug")){
			obj.logDebug(message);
		} else if (severity.equals("trace")){
			obj.logTrace(message);
		}
		
	}
	
	private void logTrace(String message) {
		logger.trace(message);
	}

	private void logDebug(String message) {
		logger.debug(message);
	}
	
	private void logWarning(String message) {
		logger.warn(message);
	}

	private void logError(String message) {
		logger.error(message);
	}

	private void logFatal(String message) {
		logger.fatal(message);
	}
	
	private void logInfo(String message) {
		logger.info(message);
	}

}

/*PUT IN POM.XML DEPENDENCIES:

<dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
</dependency>

*/
