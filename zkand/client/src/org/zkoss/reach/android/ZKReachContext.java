package org.zkoss.reach.android;

import android.view.ViewGroup;

public class ZKReachContext {
	
	private ViewGroup _errorView=null;
	private ViewGroup _loadingView=null;
	
	public ZKReachContext(ViewGroup errorView, ViewGroup loadingView) {
		if (errorView == null) {
			throw new IllegalArgumentException("The error view cannot be null");
		}
		
		if (loadingView == null) {
			throw new IllegalArgumentException("The loading view cannot be null");
		}
		
		_errorView = errorView;
		_loadingView = loadingView;
	}

	public ViewGroup getErrorView() {
		return _errorView;
	}

	public ViewGroup getLoadingView() {
		return _loadingView;
	}
}
