package org.zkoss.reach.android.handle;

import org.json.JSONArray;
import org.zkoss.reach.android.exceptions.handle.HandleException;
import org.zkoss.reach.android.exceptions.handle.invoke.InvokeException;
import org.zkoss.reach.android.exceptions.handle.parse.ParseException;
import org.zkoss.reach.android.handle.invoke.Invoker;
import org.zkoss.reach.android.handle.parse.Parser;

import android.content.Context;
import android.view.ViewGroup;


public abstract class AbstractHandler<E> implements Handler<E> {
	
	public abstract Parser<E> getParser();
	public abstract Invoker<E> getInvoker();
	
	public void handle(JSONArray json, ViewGroup parent, Context context) throws HandleException {
		
		if(getParser() == null || getInvoker() == null) {
			throw new IllegalArgumentException("The parser or invoker cannot be null");
		}
		
		E result = null;
		try {
			result = getParser().parse(json);
			getInvoker().invoke(result, parent, context);
		} catch (ParseException e) {
			throw new HandleException("Error when parsing the JSON using the parser: " + getParser().getClass().getName() +
								  	  "for JSON: " + json, e);
		} catch (InvokeException e) {
			
			String resultDump = "(parser result is null, unable to dump)";
			String resultType = "(parser result is null, type unavailable)";
			
			if(result != null) {
				resultDump = result.toString();
				resultType = result.getClass().getName();
			}
			
			throw new HandleException("Error when invoking: " + getInvoker().getClass().getName() +
				  	  				  " toString dump of invoker object (" + resultType +") :" + resultDump, e);
		}
	}
}