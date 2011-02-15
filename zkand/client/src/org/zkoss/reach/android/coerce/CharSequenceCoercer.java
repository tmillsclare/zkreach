package org.zkoss.reach.android.coerce;


public class CharSequenceCoercer implements ReachCoercible<CharSequence> {

	@Override
	public CharSequence coerce(String string) {
		//no need for a conversion here just a simple return will do
		return string;
	}

}
