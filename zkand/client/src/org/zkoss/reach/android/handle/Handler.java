package org.zkoss.reach.android.handle;

import org.json.JSONArray;
import org.zkoss.reach.android.exceptions.handle.HandleException;
import org.zkoss.reach.android.handle.invoke.Invoker;
import org.zkoss.reach.android.handle.parse.Parser;

import android.content.Context;
import android.view.ViewGroup;

public interface Handler<E> {
	public Parser<E> getParser();
	public Invoker<E> getInvoker();
	public void handle(JSONArray json, ViewGroup parent, Context context) throws HandleException;
}
