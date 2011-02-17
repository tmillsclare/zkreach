package org.zkoss.reach.android.handle;

import org.zkoss.reach.android.handle.invoke.DefaultInvoker;
import org.zkoss.reach.android.handle.invoke.Invoker;
import org.zkoss.reach.android.handle.parse.DefaultParser;
import org.zkoss.reach.android.handle.parse.Parser;

public class PageHandler extends BaseHandler<Widget> {
	
	private final Invoker<Widget> defaultInvoker = new DefaultInvoker();
	private final Parser<Widget> defaultParser = new DefaultParser();
	
	
	protected Invoker<Widget> getInvoker() {
		return defaultInvoker;
	}

	protected Parser<Widget> getParser() {
		return defaultParser;
	}

}
