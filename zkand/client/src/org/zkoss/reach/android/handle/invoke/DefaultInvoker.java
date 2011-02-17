package org.zkoss.reach.android.handle.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.zkoss.reach.android.coerce.CoerceType;
import org.zkoss.reach.android.coerce.ReachCoercible;
import org.zkoss.reach.android.exceptions.handle.invoke.InvokeException;
import org.zkoss.reach.android.handle.Widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class DefaultInvoker implements Invoker<Widget> {

	@Override
	public void invoke(Widget object, ViewGroup parent, Context context)
			throws InvokeException {
		for (Widget zkc : object.getChildren()) {
			invokeClass(zkc, parent, context);
		}
	}
	
	private void invokeClass(Widget component, ViewGroup parent,
			Context context) throws InvokeException {

		// TODO: Make a decision as to what happens at this point of the
		// code
		if (component != null) {
			

			// TODO Null check needed here
			// TODO instantiate correct class using Class.forName
			Class<View> cls = null;
			Constructor<View> constructor = null;
			View view = null;

			try {
				cls = (Class<View>) Class.forName(component.getClsName());
				constructor = cls.getConstructor(Context.class);
 				view = constructor.newInstance(context);
 				
 				//loop through all attributes to add them
 				for(Map.Entry<String, String> entry: component.getAttributes().entrySet()) {
 					
 					final String key = entry.getKey();
 					final String uppercase = key.substring(0, 1).toUpperCase() + key.substring(1);
 					
 					StringBuilder getBean = new StringBuilder("get");
 					getBean.append(uppercase);
 					
 					StringBuilder setBean = new StringBuilder("set");
 					setBean.append(uppercase);
 					
 					final Method getMethod = cls.getMethod(getBean.toString(), null);	
 					final Class<?> retType = getMethod.getReturnType();
 					
 					ReachCoercible<?> coercible = CoerceType.getType(retType);
 					
 					if (coercible == null) {
 						//TODO throw a Reach exception here
 					}
 					
 					final Method setMethod = cls.getMethod(setBean.toString(), retType);
 					
 					// TODO handle the exception or null
 					setMethod.invoke(view, coercible.coerce(component.getClsName(), entry.getValue()));
 				}
			} catch (IllegalArgumentException e) {
				throw new InvokeException("There is an illegal argument", e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				Throwable ex = e.getTargetException();
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// TODO check for null
			parent.addView(view);

			for (Widget zkc : component.getChildren()) {
				invokeClass(zkc, parent, context);
			}
		}
	}

}
