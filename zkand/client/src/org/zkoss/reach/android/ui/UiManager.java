package org.zkoss.reach.android.ui;

import java.util.Hashtable;

import org.zkoss.reach.android.ui.factory.ButtonBuilder;
import org.zkoss.reach.android.ui.factory.TextViewBuilder;

public class UiManager {
	private static Hashtable<String, UiBuilder> uiFactoryMap = new Hashtable<String, UiBuilder>(16);
	
	static {
		new ButtonBuilder();
		new TextViewBuilder();
	}
	
	public static void registerBuilder(String className, UiBuilder builder) {
		uiFactoryMap.put(className, builder);
	}
}
