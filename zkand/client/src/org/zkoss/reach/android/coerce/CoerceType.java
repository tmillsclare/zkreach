package org.zkoss.reach.android.coerce;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.reach.android.coerce.types.CharSequenceCoercer;


public class CoerceType {
	
	private static Map<Class<?>, ReachCoercible<?>> coercionMap = new HashMap<Class<?>, ReachCoercible<?>>(); 
	
	static {
		//default types
		coercionMap.put(CharSequence.class, new CharSequenceCoercer());
	}
	
	public static void registerCoercion(Class<?> type, ReachCoercible<?> coercible) {
		coercionMap.put(type, coercible);
	}
	
	public static ReachCoercible<?> getType(Class<?> type) {
		return coercionMap.get(type);
	}
}
