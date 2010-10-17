/* Button.java

	Purpose:
		
	Description:
		
	History:
		16 Oct 2010 12:02:18     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.server.components;

import java.io.IOException;

import org.zkoss.reach.android.server.impl.ReachComponent;
import org.zkoss.zk.ui.sys.ContentRenderer;

/**
 * Implementation of the button for reach
 * 
 * @author timothyclare
 *
 */
public class Button extends ReachComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2159610396953674162L;
	
	
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
		if(!text.equals(text)) {
			_text = text;
			smartUpdate("text", text);
		}
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.AbstractComponent#renderProperties(org.zkoss.zk.ui.sys.ContentRenderer)
	 */
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		
		super.renderProperties(renderer);
		render(renderer, "text", getText());
	}
}
