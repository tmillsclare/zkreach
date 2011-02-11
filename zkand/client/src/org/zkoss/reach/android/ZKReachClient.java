package org.zkoss.reach.android;

import java.net.URI;

import org.zkoss.reach.android.exceptions.parsing.ParserRegisterationException;
import org.zkoss.reach.android.parse.PageCommandHandler;
import org.zkoss.reach.android.parse.ParserCommand;
import org.zkoss.reach.android.parse.ReachParser;

import android.app.Activity;
import android.os.Bundle;

public class ZKReachClient extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
			ReachParser.registerParser(ParserCommand.PAGE, new PageCommandHandler());
		} catch (ParserRegisterationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Page myPage = new Page(this, URI.create("http://10.1.3.116:8080/zkreach-server/button.izul"));
        setContentView(myPage.getLayout());
    }
}