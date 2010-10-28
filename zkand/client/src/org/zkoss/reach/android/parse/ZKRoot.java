/* ZKRoot.java

	Purpose:
		
	Description:
		
	History:
		23 Oct 2010 22:29:14     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.parse;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author Tim
 *
 */
public class ZKRoot extends ZKComponent {
	
	/**
	 * @param comp
	 * @throws JSONException
	 */
	public ZKRoot(JSONArray comp) throws JSONException {
		super(comp);
	}

	
	/* (non-Javadoc)
	 * @see org.zkoss.zkreach.parsing.ZKComponent#getId()
	 */
	@Override
	public String getId() {
		return "android.Page";
	}
}
