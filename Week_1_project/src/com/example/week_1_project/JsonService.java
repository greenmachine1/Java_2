package com.example.week_1_project;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.util.Log;

public class JsonService extends IntentService{

	public static final String NAME_OF_BAND = "band";
	
	public JsonService() {
		super("JsonService");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("this is done", "With a service");
		
		// this has to do with my messenger from the main activity
		Bundle extras = intent.getExtras();
		String nameOfPassedInBand = (String) extras.get(NAME_OF_BAND);
		
		Log.i("Passed in name", nameOfPassedInBand);
	}

}
