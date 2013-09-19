package com.Cory.week_3_final_project;

import com.Cory.lib.WebInfo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



/*
 * This activity will fetch data through json and display it 
 * depending on the users input
 */

public class BandInfo extends Activity {
	
	TextView topTextBox;
	
	Context _context;
	Boolean _connected;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bandinfo);
		
		_context = this;
		
		// getting the user information through the getIntent method
		Bundle data = getIntent().getExtras();
		if(data != null){
			String newString = data.getString("userInput");
			String tempBoolString = "";
			
			Boolean connectedBool = data.getBoolean("isConnected");
			if(connectedBool == true){
				tempBoolString = "True";
			}
			else{
				tempBoolString = "False";
			}
			
			
			topTextBox = (TextView)findViewById(R.id.topText);
			topTextBox.setText("Here are the results for " + newString + " " + tempBoolString);
			
			Log.i("user", "" + newString);
		}
	}
}
