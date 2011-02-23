package org.zkoss.reach.android;

import java.net.URI;

import org.json.JSONException;
import org.zkoss.reach.android.handle.HandlerManager;
import org.zkoss.reach.android.http.HttpDownload;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Page extends LinearLayout {
	
	private URI _location;
	
	public Page(Context context, URI location) {
		
		super(context);
		
		if(location == null) {
			throw new IllegalArgumentException("The page location cannot be null");
		}
		
		_location = location;
		String json = HttpDownload.fromUri(location);
		
		try {
			HandlerManager.handle(json, this, context);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	public URI getLocation() {
		return _location;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		int count = getChildCount();
		
		if (count != 1) {
			throw new IllegalStateException("Page must have only one child component");
		}
		
		View child = getChildAt(0);
		
		if (!(child instanceof ViewGroup)) {
			throw new IllegalStateException("Page child must extend from ViewGroup");
		}
		
		super.onLayout(changed, l, count, r, b);
	}
}
