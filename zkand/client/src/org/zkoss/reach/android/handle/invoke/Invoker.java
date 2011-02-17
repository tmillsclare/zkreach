package org.zkoss.reach.android.handle.invoke;

import org.zkoss.reach.android.exceptions.handle.invoke.InvokeException;

import android.content.Context;
import android.view.ViewGroup;

public interface Invoker<E> {
	void invoke(E object, ViewGroup parent, Context context) throws InvokeException;
}
