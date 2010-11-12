package org.zkoss.reach.android.parse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.reach.android.exceptions.parsing.ParserRegisterationException;

import android.view.ViewGroup;

public class ReachParser {
	
	private static Map<ParserCommand, CommandHandler<?>> _registeredHandler = new HashMap<ParserCommand, CommandHandler<?>>();
	
	public static void registerParser(ParserCommand parserCommand, CommandHandler<?> ch) throws ParserRegisterationException {
		if(_registeredHandler.containsKey(parserCommand)) {
			throw new ParserRegisterationException("Parser for " + parserCommand.toString() + " already exist. If you would like to override it please use the overrideParser method");
		}
		
		_registeredHandler.put(parserCommand, ch);
	}
	
	public static CommandHandler<?> registerParserOrOverride(ParserCommand parserCommand, CommandHandler<?> ch) {
		return _registeredHandler.put(parserCommand, ch);
	}
	
	@SuppressWarnings("rawtypes")
	public static void parse(String json, ViewGroup root) throws JSONException {
		JSONObject object = new JSONObject(json);
		
		Iterator iterator = object.keys();
		
		while(iterator.hasNext()) {
			String command = (String)iterator.next();
			
			for (Map.Entry<ParserCommand, CommandHandler<?>> entry : _registeredHandler.entrySet()) {
				
				//let's get the string value of the ParserCommand
				String parserCommand = entry.getKey().toString();
				
				if(parserCommand.equals(command)) {
					JSONArray contents = object.getJSONArray(command);
	
					CommandHandler<?> ch = entry.getValue();
					ch.handle(contents, root);
				}
			}
		}
	}
}
