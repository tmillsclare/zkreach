package org.zkoss.reach.android.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.zkoss.reach.android.ZKReachConstants;

import android.util.Log;

public final class HttpDownload {
	public static String fromUri(URI uri) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);
		HttpResponse response = null;
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		
		try {
			response = client.execute(get);
			
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (ClientProtocolException e) {
			Log.e(ZKReachConstants.ZKReachTag, "ClientProtocolException", e);
		} catch (IOException e) {
			Log.e(ZKReachConstants.ZKReachTag, "IOException", e);
		} catch (IllegalStateException e) {
			Log.e(ZKReachConstants.ZKReachTag, "IllegalStateException", e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Log.e(ZKReachConstants.ZKReachTag, "IOException", e);
				}
			}
		}
		
		return sb.toString();		
	}
}
