package org.zkoss.reach.android.exceptions.handle.parse;

import org.zkoss.reach.android.exceptions.ReachException;

public class ParserRegisterationException extends ReachException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8469877469777565993L;
	
	public ParserRegisterationException(String string) {
		super(string);
	}
	
	public ParserRegisterationException(String string, Exception ex) {
		super(string, ex);
	}
}
