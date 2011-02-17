/* ReachDevice.java

	Purpose:
		
	Description:
		
	History:
		Thu Aug 23 18:38:54     2010, Created by timothyclare

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.reach.android.device;

import org.zkoss.zk.device.GenericDevice;

/**
 * Represents the ZK Reach JSON output.
 * 
 * @author timothyclare
 */
public class ReachDevice extends GenericDevice {

	@Override
	public String getContentType() {
		return "application/json";
	}

}
