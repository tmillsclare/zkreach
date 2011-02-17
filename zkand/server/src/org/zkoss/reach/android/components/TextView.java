/* ReachLabel.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 12:52:31     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.components;

import java.io.IOException;

import org.zkoss.reach.android.impl.ReachComponent;
import org.zkoss.zk.ui.sys.ContentRenderer;

/**
 * @author timothyclare
 *
 */
public class TextView extends ReachComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662205957506607075L;
	
	private String _text;

	/**
	 * @return the text
	 */
	public String getText() {
		return _text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		if(!text.equals(_text)) {
			_text = text;
			smartUpdate("Text", text);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.AbstractComponent#renderProperties(org.zkoss.zk.ui.sys.ContentRenderer)
	 */
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		
		super.renderProperties(renderer);
		render(renderer, "Text", getText());
	}
}
