package com.Cory.week_3_final_project;

import com.Cory.lib.WebInfo;
import com.Cory.week_3_final_project.SaveData;

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
	TextView mainTextBox;
	
	Context _context;
	Boolean _connected;
	
	// for my save portion
	SaveData m_file;
    String fileName = "returned_json.txt";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bandinfo);
		
		_context = this;
		
		// Detect network connection
        _connected = WebInfo.getConnectionStatus(_context);
         if(_connected)
         {
        	 Log.i("Network Connection", WebInfo.getConnectionType(_context));
         }
		
         
		// getting the user information through the getIntent method
		Bundle data = getIntent().getExtras();
		if(data != null){
			String newString = data.getString("userInput");
			
			
			
			
			
			
			
			
			
			// this is the output section
			topTextBox = (TextView)findViewById(R.id.topText);
			topTextBox.setText("Here are the results for " + newString);
			
			mainTextBox = (TextView)findViewById(R.id.mainText);
			
			Log.i("user", "" + newString);
		}
	}
}
