package org.zkoss.reach.android.coerce.types;

import org.zkoss.reach.android.coerce.ReachCoercible;


public class CharSequenceCoercer implements ReachCoercible<CharSequence> {

	@Override
	public CharSequence coerce(String string) throws IllegalArgumentException {
		//no need for a conversion here just a simple return will do
		return string;
	}


}
