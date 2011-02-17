package org.zkoss.reach.android.handle.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.zkoss.reach.android.exceptions.handle.parse.ParseException;
import org.zkoss.reach.android.handle.Widget;

public class DefaultParser implements Parser<Widget> {

	@Override
	public Widget parse(JSONArray array) throws ParseException {
		try {
			Widget returnWidget = new Widget(array);
			return returnWidget;
		} catch (JSONException e) {
			throw new ParseException("Could not parse the given JSON", e);
		}
	}

}
