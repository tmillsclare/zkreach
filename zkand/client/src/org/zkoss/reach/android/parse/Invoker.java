package org.zkoss.reach.android.parse;

import android.view.ViewGroup;

public interface Invoker<E> {
	void invoke(E object, ViewGroup root);
}
