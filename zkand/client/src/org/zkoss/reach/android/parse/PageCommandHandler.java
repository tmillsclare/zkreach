package org.zkoss.reach.android.parse;

import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
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

	private void invokeClass(ZKComponent component, ViewGroup root,
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
 				
 				for(Map.Entry<String, String> entry: component.getOptions().entrySet()) {
 					
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
			root.addView(view);

			for (ZKComponent zkc : component.getComponents()) {
				invokeClass(zkc, root, context);
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
