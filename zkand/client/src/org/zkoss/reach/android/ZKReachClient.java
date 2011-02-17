package org.zkoss.reach.android;

import java.net.URI;

import org.zkoss.reach.android.exceptions.handle.parse.ParserRegisterationException;
import org.zkoss.reach.android.handle.HandlerManager;
import org.zkoss.reach.android.handle.PageHandler;
import org.zkoss.reach.android.handle.parse.ParserCommand;

import android.app.Activity;
import android.os.Bundle;

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
        
		Page myPage = new Page(this, URI.create("http://10.1.3.116:8080/zkreach-server/button.izul"));
        setContentView(myPage);
    }
}