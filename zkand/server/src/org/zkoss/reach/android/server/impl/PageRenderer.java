/* PageRenderer.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 10:12:49     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.server.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.Attributes;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zk.ui.sys.HtmlPageRenders;

/**
 * The renderer for Reach
 * 
 * @author tomyeh
 * @author timothyclare
 *
 */
public class PageRenderer implements org.zkoss.zk.ui.sys.PageRenderer {

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.sys.PageRenderer#render(org.zkoss.zk.ui.Page, java.io.Writer)
	 */
	@Override
	public void render(Page page, Writer out) throws IOException {
		final Execution exec = Executions.getCurrent();
		final String ctl = ExecutionsCtrl.getPageRedrawControl(exec);
		
		boolean au = exec.isAsyncUpdate(null);
		
		if (!au && (page.isComplete() || "complete".equals(ctl))) {
			renderComplete(exec, page, out);
			return;
		}

		boolean pageOnly = au;
		if (!pageOnly)
			pageOnly = (exec.isIncluded() || "page".equals(ctl))
				&& !"desktop".equals(ctl);

		if (pageOnly)
			renderPage(exec, page, out);
		else {
			exec.setAttribute(Attributes.PAGE_REDRAW_CONTROL, "complete");
			renderComplete(exec, page, out);
		}
	}
	
	private static void write(Writer out, String s) throws IOException {
		if (s != null) out.write(s);
	}
	
	/** Renders the page if {@link Page#isComplete} is false.
	 */
	protected void renderPage(Execution exec, Page page, Writer out)
	throws IOException {
		for (Iterator<ComponentCtrl> it = page.getRoots().iterator(); it.hasNext();)
			it.next().redraw(out);

	}
	
	/** Renders the page if {@link Page#isComplete} is true.
	 * In other words, the page content contains HTML/BODY tags.
	 */
	protected void renderComplete(Execution exec, Page page, Writer out)
	throws IOException {
		HtmlPageRenders.setContentType(exec, page);
		write(out, HtmlPageRenders.outFirstLine(exec, page));
		write(out, HtmlPageRenders.outDocType(exec, page));
		renderPage(exec, page, out);
	}

}
