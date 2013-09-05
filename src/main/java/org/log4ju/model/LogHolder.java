package org.log4ju.model;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LogHolder {

	private ByteArrayOutputStream baos;
	private String separator;
	
	protected LogHolder() {
		this(new ByteArrayOutputStream());
	}
	
	public LogHolder(ByteArrayOutputStream baos) {
		this.baos = baos;
		this.separator = ":";
	}
	
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	
	public void reset() {
		baos.reset();
	}
	
	public List<String[]> getMessages() {
		String msgs = baos.toString();
		List<String[]> result = new ArrayList<String[]>();
		
		String[] msgTuple = msgs.split(System.getProperty("line.separator"));
		for (int i=0; i<msgTuple.length; i++) {
			String[] split = msgTuple[i].split(separator, 2);
			if (split.length != 2) fail();
			result.add(split);
		}
		return result;
	}
}
