package org.log4ju.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.helpers.MessageFormatter;


public class LogMessage implements Comparable<LogMessage> {

	private LogLevel level;
	private String message;
	
	public static LogMessage errorMessage(String message) {
		return errorMessage(message, null);
	}
	
	public static LogMessage warnMessage(String message) {
		return warnMessage(message, null);
	}
	
	public static LogMessage infoMessage(String message) {
		return infoMessage(message, null);
	}
	
	public static LogMessage debugMessage(String message) {
		return debugMessage(message, null);
	}
	
	public static LogMessage traceMessage(String message) {
		return traceMessage(message, null);
	}
	
	public static LogMessage errorMessage(String message, Object[] params) {
		return message(LogLevel.ERROR, message, params);
	}
	
	public static LogMessage warnMessage(String message, Object[] params) {
		return message(LogLevel.WARN, message, params);
	}
	
	public static LogMessage infoMessage(String message, Object[] params) {
		return message(LogLevel.INFO, message, params);
	}
	
	public static LogMessage debugMessage(String message, Object[] params) {
		return message(LogLevel.DEBUG, message, params);
	}
	
	public static LogMessage traceMessage(String message, Object[] params) {
		return message(LogLevel.TRACE, message, params);
	}
	
	public static LogMessage message(LogLevel level, String message) {
		return new LogMessage(level, message);
	}
	
	public static LogMessage message(LogLevel level, String message, Object[] params) {
		if (params != null) {
			message = MessageFormatter.arrayFormat(message, params).getMessage();
		}
		return message(level, message);
	}
	
	public static LogMessage[] toMessages(List<String[]> messages) {
		List<LogMessage> res = new ArrayList<LogMessage>(messages.size());
		for (String[] tuple: messages) {
			res.add(message(LogLevel.valueOf(tuple[0]), tuple[1]));
		}
		return res.toArray(new LogMessage[messages.size()]);
	}
	
	private LogMessage(LogLevel level, String message) {
		this.level = level;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public LogLevel getScope() {
		return level;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%s", level, message);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		
		LogMessage val = (LogMessage) obj;
		return new EqualsBuilder()
					.append(level, val.level)
					.append(message, val.message)
					.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
					.append(level)
					.append(message)
					.toHashCode();
	}
	
	@Override
	public int compareTo(LogMessage o) {
		return  new CompareToBuilder()
					.append(level, o.level)
					.append(message, o.message)
					.toComparison();
	}
}
