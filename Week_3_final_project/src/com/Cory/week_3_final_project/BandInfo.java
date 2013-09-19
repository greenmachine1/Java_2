package com.Cory.week_3_final_project;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BandInfo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bandinfo);
		
		// getting the user information through the getIntent method
		Bundle data = getIntent().getExtras();
		if(data != null){
			String newString = data.getString("userInput");
			
			Log.i("user", "" + newString);
		}
	}
}
