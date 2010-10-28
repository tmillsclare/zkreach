package org.zkoss.reach.android.parse;

import org.json.JSONArray;


public abstract class CommandHandler<E> {
	
	protected abstract Parser<E> getParser();
	protected abstract Invoker<E> getInvoker();
	
	public void handle(JSONArray json) {
		
		if(getParser() == null || getInvoker() == null) {
			throw new IllegalArgumentException("The parser or invoker cannot be null");
		}
		
		E result = getParser().parse(json);
		getInvoker().invoke(result);
	}
}