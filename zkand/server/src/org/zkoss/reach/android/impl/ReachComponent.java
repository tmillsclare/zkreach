/* ReachComponent.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 10:58:55     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.impl;

import java.io.IOException;
import java.io.Writer;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.sys.ComponentRedraws;

/**
 * Reach base component
 * 
 * @author timothyclare
 *
 */
public class ReachComponent extends AbstractComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4314959622641244085L;
	
	private static final String DEFAULT = "default";
	
	private final boolean isAsyncUpdate() {
		final Execution exec = Executions.getCurrent();
		return exec != null && exec.isAsyncUpdate(getPage());
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.AbstractComponent#redraw(java.io.Writer)
	 */
	@Override
	public void redraw(Writer out) throws IOException {
		final int order = ComponentRedraws.beforeRedraw(false);
		final boolean aupg = this.isAsyncUpdate();
		
		final String extra;
		
		try {
			if (order < 0)
				out.write("[");
			else if (order > 0) //not first child
				out.write(',');

			final ReachContentRenderer renderer = new ReachContentRenderer();
			renderProperties(renderer);

			final String wgtcls = getWidgetClass();
			
			if (wgtcls == null)
				throw new UiException("Widget class required for "+this+" with "+getMold());
			
			out.write("\n['");
			out.write(wgtcls);
			out.write("','");
			out.write(getUuid());
			out.write("',{");
			out.write(renderer.getBuffer().toString());
			out.write("},[");

			redrawChildren(out);

			out.write(']');
			final String mold = getMold();
			if (!DEFAULT.equals(mold)) {
				out.write(",'");
				out.write(mold);
				out.write('\'');
			}
			out.write(']');

		} finally {
			extra = ComponentRedraws.afterRedraw();
		}
		
		if (order < 0) {
			if (aupg) {
				
				if (extra.length() > 0) {
					out.write(",0,null,'");
					out.write(Strings.escape(extra, Strings.ESCAPE_JAVASCRIPT));
					out.write('\'');
				}
				
				out.write(']');
			} else {
				out.write("];\n");
				out.write(extra);
			}
		}
	}
}
