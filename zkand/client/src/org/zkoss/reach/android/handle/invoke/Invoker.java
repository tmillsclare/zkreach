package org.zkoss.reach.android.handle.invoke;

import org.zkoss.reach.android.exceptions.handle.invoke.InvokeException;

import android.content.Context;
import android.view.View;

public interface Invoker<E> {
	View invoke(Context context, E object) throws InvokeException;
}
