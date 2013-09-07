package com.example.week_1_project;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class JsonService extends IntentService{

	public JsonService() {
		super("JsonService");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("this is done", "With a service");
	}

}
