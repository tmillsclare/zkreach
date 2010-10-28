package org.zkoss.reach.android.parse;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class PageCommandHandler extends CommandHandler<ZKRoot> implements Invoker<ZKRoot>, Parser<ZKRoot> {
	
	@Override
	public void invoke(ZKRoot object) {
		System.out.println("Invoking " + PageCommandHandler.class);		
	}

	@Override
	public ZKRoot parse(JSONArray array) {
		ZKRoot root = null;
		
		try {
			root = new ZKRoot(array);
		} catch (JSONException e) {
			Log.e("zkreach", e.getMessage());
		}
		
		return root;
	}

	@Override
	protected Invoker<ZKRoot> getInvoker() {
		return this;
	}

	@Override
	protected Parser<ZKRoot> getParser() {
		return this;
	}

}
