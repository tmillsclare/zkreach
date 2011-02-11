package org.zkoss.reach.android.ui;

public class UiBuilder {
	public UiBuilder(String className) {
		UiManager.registerBuilder(className, this);
	}
}
