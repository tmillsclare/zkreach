package org.zkoss.reach.android.handle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.reach.android.exceptions.handle.parse.ParserRegisterationException;
import org.zkoss.reach.android.handle.parse.ParserCommand;

import android.content.Context;
import android.view.ViewGroup;

public class HandlerManager {
	
	private static Map<ParserCommand, Handler<?>> _registeredHandler = new HashMap<ParserCommand, Handler<?>>();
	
	public static void registerParser(ParserCommand parserCommand, Handler<?> ch) throws ParserRegisterationException {
		if(_registeredHandler.containsKey(parserCommand)) {
			throw new ParserRegisterationException("Parser for " + parserCommand.toString() + " already exist. If you would like to override it please use the overrideParser method");
		}
		
		_registeredHandler.put(parserCommand, ch);
	}
	
	public static Handler<?> registerParserOrOverride(ParserCommand parserCommand, Handler<?> ch) {
		return _registeredHandler.put(parserCommand, ch);
	}
	
	@SuppressWarnings("unchecked")
	public static void handle(String json, ViewGroup root, Context context) throws JSONException {
		JSONObject object = new JSONObject(json);
		
		Iterator iterator = object.keys();
		
		while(iterator.hasNext()) {
			String command = (String)iterator.next();
			
			for (Map.Entry<ParserCommand, Handler<?>> entry : _registeredHandler.entrySet()) {
				
				//let's get the string value of the ParserCommand
				String parserCommand = entry.getKey().toString();
				
				if(parserCommand.equalsIgnoreCase(command)) {
					JSONArray contents = object.getJSONArray(command);
	
					Handler<?> ch = entry.getValue();
					ch.handle(contents, root, context);
				}
			}
		}
	}
}
