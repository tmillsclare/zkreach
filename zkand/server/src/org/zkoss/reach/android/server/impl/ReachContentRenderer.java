/* ReachContentRenderer.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 11:16:55     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.server.impl;

import java.util.Map;

import org.zkoss.zk.ui.sys.JsContentRenderer;

/**
 * @author timothyclare
 *
 */
public class ReachContentRenderer extends JsContentRenderer {

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.sys.JsContentRenderer#renderWidgetOverrides(java.util.Map)
	 */
	@Override
	public void renderWidgetOverrides(Map overrides) {
		throw new UnsupportedOperationException("Base methods on android cannot be overwritten");
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.sys.JsContentRenderer#renderWidgetListeners(java.util.Map)
	 */
	@Override
	public void renderWidgetListeners(Map listeners) {
		throw new UnsupportedOperationException("Widget listeners cannot be passed to android");
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.sys.JsContentRenderer#renderDirectly(java.lang.String, java.lang.Object)
	 */
	@Override
	public void renderDirectly(String name, Object value) {
		throw new UnsupportedOperationException("Rendering directly to android is not available");
	}
}
