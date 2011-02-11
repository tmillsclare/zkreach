package org.zkoss.reach.android;

import java.net.URI;

import org.json.JSONException;
import org.zkoss.reach.android.http.HttpDownload;
import org.zkoss.reach.android.parse.ReachParser;
import org.zkoss.reach.android.parse.ZKRoot;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Page {
	
	private URI _location;
	private ZKRoot _root;
	private Context _context;
	
	private LinearLayout _layout;
	
	public Page(Context context, URI location) {
		
		if(location == null) {
			throw new IllegalArgumentException("The page location cannot be null");
			
		}
		
		if(context == null) {
			throw new IllegalArgumentException("The page context cannot be null");
		}
		
		_layout = new LinearLayout(context);
		_location = location;
		String json = HttpDownload.fromUri(location);
		
		try {
			ReachParser.parse(json, getLayout(), context);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public URI getLocation() {
		return _location;
	}

	public ZKRoot getRoot() {
		return _root;
	}
	
	public Context getContext() {
		return _context;
	}
	
	public ViewGroup getLayout() {
		return _layout;
	}
}
