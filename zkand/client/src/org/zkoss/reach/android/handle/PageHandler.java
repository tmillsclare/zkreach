package org.zkoss.reach.android.handle;

import org.zkoss.reach.android.handle.invoke.DefaultInvoker;
import org.zkoss.reach.android.handle.invoke.Invoker;
import org.zkoss.reach.android.handle.parse.DefaultParser;
import org.zkoss.reach.android.handle.parse.Parser;

public class PageHandler extends AbstractHandler<Widget> {
	
	private final Invoker<Widget> defaultInvoker = new DefaultInvoker();
	private final Parser<Widget> defaultParser = new DefaultParser();
	
	
	public Invoker<Widget> getInvoker() {
		return defaultInvoker;
	}

	public Parser<Widget> getParser() {
		return defaultParser;
	}

}
