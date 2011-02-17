package org.zkoss.reach.android.handle;

import org.json.JSONArray;
import org.zkoss.reach.android.exceptions.handle.invoke.InvokeException;
import org.zkoss.reach.android.exceptions.handle.parse.ParseException;
import org.zkoss.reach.android.handle.invoke.Invoker;
import org.zkoss.reach.android.handle.parse.Parser;

import android.content.Context;
import android.view.ViewGroup;


public abstract class BaseHandler<E> {
	
	protected abstract Parser<E> getParser();
	protected abstract Invoker<E> getInvoker();
	
	public void handle(JSONArray json, ViewGroup parent, Context context) {
		
		if(getParser() == null || getInvoker() == null) {
			throw new IllegalArgumentException("The parser or invoker cannot be null");
		}
		
		E result;
		try {
			result = getParser().parse(json);
			getInvoker().invoke(result, parent, context);
		} catch (ParseException e) {
			// TODO throw a handle error to wrap
		} catch (InvokeException e) {
			// TODO throw a handle error to wrap
		}
	}
}