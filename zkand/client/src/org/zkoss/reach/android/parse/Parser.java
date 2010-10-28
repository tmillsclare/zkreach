package org.zkoss.reach.android.parse;

import org.json.JSONArray;

public interface Parser<E> extends Invoker<E> {
	E parse(JSONArray array);
}
