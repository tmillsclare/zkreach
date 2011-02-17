package org.zkoss.reach.android.exceptions.handle.parse;

import org.zkoss.reach.android.exceptions.ReachException;

public class ParseException extends ReachException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8842596250217190146L;

	public ParseException(String string, Exception ex) {
		super(string, ex);
	}

}
