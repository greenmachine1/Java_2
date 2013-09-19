package com.Cory.week_3_final_project;

import com.Cory.lib.WebInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	// global variables
	Context _context;
	
	EditText userInputText;
	Boolean _connected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		_context = this;
		
		// Detect network connection
        _connected = WebInfo.getConnectionStatus(_context);
         if(_connected)
         {
        	 Log.i("Network Connection", WebInfo.getConnectionType(_context));
         }
		
		// assigning my userInputText
		userInputText = (EditText)findViewById(R.id.userInput);

		// button for my band info retrieval
		Button bandInfoButton = (Button)findViewById(R.id.bandInfo);
		bandInfoButton.setOnClickListener(new OnClickListener(){
			

			@Override
			public void onClick(View v) {
				// setting up the intent for the next activity
				Intent myIntent = new Intent(v.getContext(), BandInfo.class);
				
				String userInputString = userInputText.getText().toString();
				
				// this will pass data to my activity (key, value)
				myIntent.putExtra("userInput", userInputString);
				myIntent.putExtra("isConnected", _connected);
				
				v.getContext().startActivity(myIntent);
				
			}
			
		});
		
		// button for my pulled info 
		Button pulledInfoButton = (Button)findViewById(R.id.goPulledInfo);
		pulledInfoButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				// setting up the intent for the next activity
				Intent myIntent = new Intent(v.getContext(), PulledInfo.class);
				v.getContext().startActivity(myIntent);
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
