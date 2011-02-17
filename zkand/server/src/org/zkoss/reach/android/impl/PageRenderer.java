/* PageRenderer.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 10:12:49     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
 */
package org.zkoss.reach.android.impl;

import java.io.IOException;
import java.io.Writer;

import org.zkoss.lang.Library;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.Attributes;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.ComponentRedraws;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zk.ui.sys.HtmlPageRenders;
import org.zkoss.zk.ui.sys.PageCtrl;

/**
 * The renderer for Reach
 * 
 * @author tomyeh
 * @author timothyclare
 * 
 */
public class PageRenderer implements org.zkoss.zk.ui.sys.PageRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.sys.PageRenderer#render(org.zkoss.zk.ui.Page,
	 * java.io.Writer)
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

	/**
	 * Renders the page if {@link Page#isComplete} is false.
	 */
	protected void renderPage(Execution exec, Page page, Writer out)
			throws IOException {
		final Desktop desktop = page.getDesktop();
		final PageCtrl pageCtrl = (PageCtrl) page;
		final Component owner = pageCtrl.getOwner();
		boolean contained = owner == null && exec.isIncluded();

		final int order = ComponentRedraws.beforeRedraw(false);

		try {
			if (order < 0)
				out.write("{\"page\":");
			else if (order > 0) // not first child
				out.write(',');
			out.write("\n[0,'"); // 0: page
			out.write(page.getUuid());
			out.write("',{");

			final StringBuffer props = new StringBuffer(128);
			final String pgid = page.getId();
			if (pgid.length() > 0)
				appendProp(props, "id", pgid);
			if (owner != null) {
				appendProp(props, "ow", owner.getUuid());
			} else {
				appendProp(props, "dt", desktop.getId());
				appendProp(props, "cu", getContextURI(exec));
				appendProp(props, "uu", desktop.getUpdateURI(null));
				appendProp(props, "ru", desktop.getRequestPath());
			}

			if (!isClientROD(page))
				appendProp(props, "z$rod", Boolean.FALSE);

			if (contained)
				appendProp(props, "ct", Boolean.TRUE);

			out.write(props.toString());
			out.write("},[");

			for (Component root = page.getFirstRoot(); root != null; root = root
					.getNextSibling())
				((ComponentCtrl) root).redraw(out);

			out.write("]]");

			out.write("}");
		} finally {

			ComponentRedraws.afterRedraw();
		}

	}

	private void appendProp(StringBuffer sb, String name, Object value) {
		if (sb.length() > 0)
			sb.append(',');
		sb.append(name).append(':');
		boolean quote = value instanceof String;
		if (quote)
			sb.append('\'');
		sb.append(value); // no escape, so use with care
		if (quote)
			sb.append('\'');
	}

	private final boolean isClientROD(Page page) {
		Object o = page.getAttribute(Attributes.CLIENT_ROD);
		if (o != null)
			return (o instanceof Boolean && ((Boolean) o).booleanValue())
					|| !"false".equals(o);

		final String s = Library.getProperty(Attributes.CLIENT_ROD);
		Boolean _crod = Boolean.valueOf(s == null || !"false".equals(s));
		return _crod.booleanValue();
	}

	private String getContextURI(Execution exec) {
		if (exec != null) {
			String s = exec.encodeURL("/");
			int j = s.lastIndexOf('/'); // might have jsessionid=...
			return j >= 0 ? s.substring(0, j) + s.substring(j + 1) : s;
		}
		return "";
	}

	/**
	 * Renders the page if {@link Page#isComplete} is true. In other words, the
	 * page content contains HTML/BODY tags.
	 */
	protected void renderComplete(Execution exec, Page page, Writer out)
			throws IOException {
		HtmlPageRenders.setContentType(exec, page);
		renderPage(exec, page, out);
	}

}
