/* ReachLabel.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 12:52:31     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.server.components;

import java.io.IOException;

import org.zkoss.reach.android.server.impl.ReachComponent;
import org.zkoss.zk.ui.sys.ContentRenderer;

/**
 * @author timothyclare
 *
 */
public class Label extends ReachComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662205957506607075L;
	
	private String _value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return _value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		_value = value;
	}
	
	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.AbstractComponent#renderProperties(org.zkoss.zk.ui.sys.ContentRenderer)
	 */
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		
		super.renderProperties(renderer);
		render(renderer, "value", getValue());
	}
}
