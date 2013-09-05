package org.log4ju.model;


public class LogMessage {

	private LogScope scope;
	private String message;
	
	public static LogMessage errorMessage(String message) {
		return new LogMessage(LogScope.ERROR, message);
	}
	
	public static LogMessage warnMessage(String message) {
		return new LogMessage(LogScope.WARN, message);
	}
	
	public static LogMessage infoMessage(String message) {
		return new LogMessage(LogScope.INFO, message);
	}
	
	public static LogMessage debugMessage(String message) {
		return new LogMessage(LogScope.DEBUG, message);
	}
	
	public static LogMessage traceMessage(String message) {
		return new LogMessage(LogScope.TRACE, message);
	}
	
	public static LogMessage message(LogScope scope, String message) {
		return new LogMessage(scope, message);
	}
	
	private LogMessage(LogScope scope, String message) {
		this.scope = scope;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public LogScope getScope() {
		return scope;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%s", scope, message);
	}
}
