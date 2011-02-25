package org.zkoss.reach.android;

import java.net.URI;

import org.zkoss.reach.android.exceptions.handle.HandleException;
import org.zkoss.reach.android.handle.HandlerManager;
import org.zkoss.reach.android.http.HttpDownload;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Page extends LinearLayout {
	
	private URI _location;
	private ZKReachContext _reachContext;
	
	public Page(Context context, ZKReachContext reachContext, URI location) {
		
		super(context);
		
		if (reachContext == null) {
			throw new IllegalArgumentException("The reach context cannot be null");
		}
		
		if(location == null) {
			throw new IllegalArgumentException("The page location cannot be null");
		}
		
		_location = location;
		_reachContext = reachContext;
		
		refresh();
	}

	public URI getLocation() {
		return _location;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		int count = getChildCount();
		
		if (count > 0) {
			View child = getChildAt(0);
			
			if (!(child instanceof ViewGroup)) {
				throw new IllegalStateException("Page child must extend from ViewGroup");
			}
		}
		
		super.onLayout(changed, l, count, r, b);
	}
	
	protected void refresh() {
		String json = HttpDownload.fromUri(_location);
		
		this.addView(_reachContext.getLoadingView());
		
		try {
			HandlerManager.handle(json, this, this.getContext());
		} catch (HandleException e) {
			this.addView(_reachContext.getErrorView());
		}
		
		this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	@Override
	public void addView(View child, int width, int height) {
		this.removeAllViews();
		super.addView(child, width, height);
	}

	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		this.removeAllViews();
		super.addView(child, index, params);
	}

	@Override
	public void addView(View child, int index) {
		this.removeAllViews();
		super.addView(child, index);
	}

	@Override
	public void addView(View child, android.view.ViewGroup.LayoutParams params) {
		this.removeAllViews();
		super.addView(child, params);
	}

	@Override
	public void addView(View child) {
		this.removeAllViews();
		super.addView(child);
	}
}
