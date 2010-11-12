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

public final class HttpDownload {
	public static String fromUri(URI uri) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);
		HttpResponse response = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();		
	}
}
