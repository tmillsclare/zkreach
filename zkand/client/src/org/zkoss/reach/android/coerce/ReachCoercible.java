package org.zkoss.reach.android.coerce;

public interface ReachCoercible<E> {
	// TODO throw exception in this interface or return null
	E coerce(String string);
}
