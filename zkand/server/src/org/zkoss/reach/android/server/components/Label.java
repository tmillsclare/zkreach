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
	private String _prologContent;

	/**
	 * @return the _prologContent
	 */
	public String getPrologContent() {
		return _prologContent;
	}

	/**
	 * @param prologContent the _prologContent to set
	 */
	public void setPrologContent(String prologContent) {
		_prologContent = prologContent;
	}
	
	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.AbstractComponent#renderProperties(org.zkoss.zk.ui.sys.ContentRenderer)
	 */
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		
		super.renderProperties(renderer);
		
		render(renderer, "prologContent", getPrologContent());
	}
}
