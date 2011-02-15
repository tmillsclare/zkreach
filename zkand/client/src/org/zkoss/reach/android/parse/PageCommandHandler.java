package org.zkoss.reach.android.parse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.zkoss.reach.android.coerce.CoerceType;
import org.zkoss.reach.android.coerce.ReachCoercible;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class PageCommandHandler extends CommandHandler<ZKRoot> implements
		Invoker<ZKRoot>, Parser<ZKRoot> {

	@Override
	public void invoke(ZKRoot object, ViewGroup root, Context context) {
		for (ZKComponent zkc : object.getComponents()) {
			invokeClass(zkc, root, context);
		}
	}

	private void invokeClass(ZKComponent component, ViewGroup parent,
			Context context) {

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
 				for(Map.Entry<String, String> entry: component.getOptions().entrySet()) {
 					
 					final String key = entry.getKey();
 					final String uppercase = key.substring(0, 1).toUpperCase() + key.substring(1);
 					
 					StringBuilder getBean = new StringBuilder("get");
 					getBean.append(uppercase);
 					
 					StringBuilder setBean = new StringBuilder("set");
 					setBean.append(uppercase);
 					
 					Method methods[] = cls.getMethods();
 					
 					final Method getMethod = cls.getMethod(getBean.toString(), null);	
 					final Class<?> retType = getMethod.getReturnType();
 					
 					ReachCoercible<?> coercible = CoerceType.getType(retType);
 					
 					if (coercible == null) {
 						//TODO throw a Reach exception here
 					}
 					
 					final Method setMethod = cls.getMethod(setBean.toString(), retType);
 					
 					// TODO handle the exception or null
 					setMethod.invoke(view, coercible.coerce(entry.getValue()));
 				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

			for (ZKComponent zkc : component.getComponents()) {
				invokeClass(zkc, parent, context);
			}
		}
	}

	@Override
	public ZKRoot parse(JSONArray array) {
		ZKRoot root = null;

		try {
			root = new ZKRoot(array);
		} catch (JSONException e) {
			Log.e("zkreach", e.getMessage());
		}

		return root;
	}

	@Override
	protected Invoker<ZKRoot> getInvoker() {
		return this;
	}

	@Override
	protected Parser<ZKRoot> getParser() {
		return this;
	}

}
