package com.example.week_1_project;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.example.lib.Json.infoRequest;
import com.example.lib.WebInfo;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/* The point of this service call is to basically load my json data into
 * storage behind the scenes
 */

public class JsonService extends IntentService{

	public static final String NAME_OF_BAND = "messenger";
	public static final String KEY_OF_THINGS = "key";
	
	public JsonService() {
		super("JsonService");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		// this has to do with my messenger from the main activity
		Bundle extras = intent.getExtras();
		
		// loading in the passed in name of the band we wish to get more info on.
		Messenger messenger = (Messenger) extras.get(NAME_OF_BAND);
		String keyOfThings = (String) extras.get(KEY_OF_THINGS);

		// calling on my returnJsonData method
		returnJsonData(keyOfThings);
		
		
		Message message = Message.obtain();
		message.arg1 = Activity.RESULT_OK;
		message.obj = "messenger " + keyOfThings;
		
		try {
			messenger.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			Log.i("OnHandleIntent", e.getMessage().toString());
			e.printStackTrace();
		}
		
	}
	
	// method used to get the JSON data
	public String returnJsonData(String userInput){
		
		// creation of url
		String completeURL = "https://itunes.apple.com/search?term=" + userInput + "&entity=musicArtist&limit=1";
		
		return null;
	}
}
