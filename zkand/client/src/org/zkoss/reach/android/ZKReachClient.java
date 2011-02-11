package org.zkoss.reach.android;

import java.net.URI;

import android.app.Activity;
import android.os.Bundle;

public class ZKReachClient extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new Page(this, URI.create("http://10.1.3.116:8080/zkreach-server/button.izul"));
    }
}