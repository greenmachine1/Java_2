/*
 * Project		Week_1_project
 * 
 * Package		com.example.week_1_project
 * 
 * @author		Cory Green
 * 
 * Date			Sep 4, 2013
 */
package com.example.week_1_project;

import com.example.lib.Json;
import com.example.lib.WebInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.app.Activity;
import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private TextView resultText;
	// creating my global variables
    Context _context;
    Boolean _connected = false;
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
         
         // creation of my go button
         Button button = (Button) findViewById(R.id.goButton);
         button.setOnClickListener(new OnClickListener() {
         
        	 // what happens when button is clicked!
			@Override
			public void onClick(View v) {
				
				// targetting the main edit text box
				EditText userEnteredField = (EditText) findViewById(R.id.userEnteredText);
				String inputString = userEnteredField.getText().toString();
				
				Log.i("User input", inputString);
				
				// calling my json class
				Json newJson = new Json();
				
				Log.i("returned info", newJson.returnJsonData(inputString));
				
				// my handler.  Handles the return info
				
				Handler JsonHandler = new Handler(){

					@Override
					public void handleMessage(Message msg) {
						// TODO Auto-generated method stub
						
						String response = null;
						if(msg.arg1 == RESULT_OK && msg.obj != null){
							try{
							response = (String) msg.obj;
							} catch(Exception e){
								Log.e("", e.getMessage().toString());
							}
							
							resultText.setText(response);
						}
					}
		    		
		    	}; 
				
				
				
		    	Messenger jsonMessenger = new Messenger(JsonHandler);
				
				Intent myServiceIntent = new Intent(_context, JsonService.class);
				
				// basically this passes info to my service
				myServiceIntent.putExtra(JsonService.NAME_OF_BAND, jsonMessenger);
				startService(myServiceIntent);
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
