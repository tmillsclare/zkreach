/* ZKComponent.java

	Purpose:
		
	Description:
		
	History:
		23 Oct 2010 22:29:28     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.parse;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * @author Tim
 *
 */
public class ZKComponent {
	
	String _clsName = null;
	String _id = null;
	Map<String, String> _options = new HashMap<String, String>();
	List<ZKComponent> _components = new ArrayList<ZKComponent>();
	
	
	
	/**
	 * @return the _clsName
	 */
	public String getClsName() {
		return _clsName;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return _id;
	}



	/**
	 * @return the _options
	 */
	public Map<String, String> getOptions() {
		return _options;
	}



	/**
	 * @return the _components
	 */
	public List<ZKComponent> getComponents() {
		return _components;
	}



	/**
	 * @param comp
	 * @throws JSONException 
	 */
	@SuppressWarnings("unchecked")
	public ZKComponent(JSONArray comp) throws JSONException {
		_clsName = comp.getString(0);
		_id = comp.getString(1);
		
		JSONObject obj = comp.getJSONObject(2);
		Iterator iterator = obj.keys();
		
		while(iterator.hasNext()) {
			String key=(String)iterator.next().toString();
			_options.put(key, obj.getString(key));
		}
		
		JSONArray comps = comp.getJSONArray(3);
		if(comps.length() > 0) {
			
			for(int i=0; i<comps.length(); i++) {
				JSONArray com = comps.getJSONArray(i);
				_components.add(new ZKComponent(com));
			}
		}

	}
	
	public boolean hasChildren() {
		return _components.size() > 0;
	}
	
	public String toString() {
		//build xml output
		XmlSerializer xml = Xml.newSerializer();
		StringWriter strWriter = new StringWriter();
		String strComponent = null;
		
		try {
			xml.setOutput(strWriter);
			xml.startDocument(null, Boolean.valueOf(true));
			xml.startTag(null, _clsName);
			
			for(String key: getOptions().keySet()) {
				xml.attribute(null, key, getOptions().get(key));
			}
			
			xml.endTag(null, _clsName);
			xml.endDocument();
			
			
			strComponent = strWriter.toString();
			
			//TODO:Null check
			strWriter.close();
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strComponent;
		
	}
}
