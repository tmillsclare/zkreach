package org.zkoss.reach.android;

import java.net.URI;

import org.zkoss.reach.android.exceptions.handle.parse.ParserRegisterationException;
import org.zkoss.reach.android.handle.HandlerManager;
import org.zkoss.reach.android.handle.PageHandler;
import org.zkoss.reach.android.handle.parse.ParserCommand;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ZKReachClient extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
			HandlerManager.registerParser(ParserCommand.PAGE, new PageHandler());
		} catch (ParserRegisterationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final ZKReachContext reachContext = new ZKReachContext(getErrorPage(this), getLoadingPage(this));
        
		Page myPage = new Page(this, reachContext, URI.create("http://10.1.3.116:9090/zkreach-server/button.izul"));
        setContentView(myPage);
    }
    
    private final ViewGroup getErrorPage(Context context) {
    	final LinearLayout ll = new LinearLayout(context);
    	final TextView tv = new TextView(context);
    	tv.setText("Error on page");
    	ll.addView(tv);
    	
    	ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    	
    	return ll;
    }
    
    private final ViewGroup getLoadingPage(Context context) {
    	final LinearLayout ll = new LinearLayout(context);
    	final TextView tv = new TextView(context);
    	tv.setText("Page is loading");
    	ll.addView(tv);
    	    	
    	ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    	
    	return ll;
    }
}