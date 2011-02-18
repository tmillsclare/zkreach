package org.zkoss.reach.android.coerce.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.reach.android.coerce.ReachCoercible;

import android.view.ViewGroup.LayoutParams;


public class LayoutParamsCoercer implements ReachCoercible<LayoutParams> {
	
	private final Pattern stringFormat = Pattern.compile("^(\\d+),(\\d+)$");
	private final String errorString = "Coercion error, the argument %s is not valid, format must be ^\\d+,\\d+$";
	
	public LayoutParams coerce(String string) throws IllegalArgumentException {
		
		//let's see if the format is correct
		Matcher m = stringFormat.matcher(string);
		boolean match = m.matches();
		
		if(!match) {
			notValid(string);
		}
		
		//at this point we guarantee that the first two are digits
		//hence extract and parse
		int width, height;
		
		width=Integer.parseInt(m.group(1));
		height=Integer.parseInt(m.group(2));
		
		return new LayoutParams(width, height);	
	}
	
	private void notValid(String string) throws IllegalArgumentException {
		throw new IllegalArgumentException(String.format(errorString, string));
	}
}
