/*
 * Project		Week_1_project
 * 
 * Package		com.example.lib
 * 
 * @author		Cory Green
 * 
 * Date			Sep 4, 2013
 */
package com.example.lib;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.lib.WebInfo;
import com.example.week_1_project.MainActivity;



import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.util.Log;



// this class will pull json data from apple api
public class Json extends MainActivity{


	public static final Context Json = null;
	String fileName = "json_string.txt";
	FileManager fileManager;
	
	
	
	public String returnJsonData(String passedInUserInput){	
		
		
		// creation of url
		String baseURL = "https://itunes.apple.com/search?term=";
		String completeURL = baseURL + passedInUserInput + "&entity=musicArtist&limit=1";
		String as = "";
	
		try{
			as = URLEncoder.encode(completeURL, "UTF-8");
		}catch(Exception e){
			Log.e("Bad URL", "Encoding problem");
			as = "";
		}
	
		URL finalURL;
		try{
			// dont actually need my UTF-8 involved in the url
			finalURL = new URL(completeURL);
			infoRequest newRequest = new infoRequest();
			newRequest.execute(finalURL);
			if(newRequest != null){
				return "Sure";
			}
	
			}catch(MalformedURLException e){
			Log.e("Bad Url", "malformed URL");
			finalURL = null;
			}
	
	
			return null;
		}
	
	
		// this actually sends out the request
		public class infoRequest extends AsyncTask<URL, Void, String>{
	
			String artistName = "";
			String primaryGenre = "";
			String artistLinkUrl = "";
	
	
			@Override
			protected String doInBackground(URL... urls) {
				String response = "";
				for(URL url: urls){
					response = WebInfo.getURLStringResponse(url);
				}
	
				return response;
			}
			// this is what comes back!
			protected void onPostExecute(String result){
				
				try {
					
					
					JSONObject json = new JSONObject(result);
					JSONArray results = json.getJSONArray("results");
	
					artistName = results.getJSONObject(0).getString("artistName").toString();
					primaryGenre = results.getJSONObject(0).getString("primaryGenreName").toString();
					artistLinkUrl = results.getJSONObject(0).getString("artistLinkUrl").toString();
	
					String fullString = artistName + artistLinkUrl + primaryGenre;
					
					
					
					
					Log.i("name", artistName);
					Log.i("name", primaryGenre);
					Log.i("name", artistLinkUrl);
	
	
	
	
	
	
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("Nope", "No such file");
				}
	
				//Log.i("Yes", artistName);
	
			}
		}
}