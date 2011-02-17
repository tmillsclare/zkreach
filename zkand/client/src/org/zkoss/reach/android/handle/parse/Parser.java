package org.zkoss.reach.android.handle.parse;

import org.json.JSONArray;
import org.zkoss.reach.android.exceptions.handle.parse.ParseException;

public interface Parser<E> {
	E parse(JSONArray array) throws ParseException;
}
