package com.Cory.week_2_project;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.week_2_project.FileManager;
import com.Cory.lib.WebInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	// creating my global variables
    Context _context;
    Boolean _connected = false;
    TextView text;
    ListView listView;
    
    FileManager m_file;
    String fileName = "returned_json.txt";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        _context = this;
        
        text = (TextView)findViewById(R.id.resultText);
        
        // targetting my listView
        listView = (ListView)this.findViewById(R.id.list);
        View listHeader = this.getLayoutInflater().inflate(R.layout.list_header, null);
        listView.addHeaderView(listHeader);
        
        
        // Detect network connection
        _connected = WebInfo.getConnectionStatus(_context);
         if(_connected)
         {
        	 Log.i("Network Connection", WebInfo.getConnectionType(_context));
         }
         
         // basically this is the cache data that gets displayed if internet connection is
         // not available
         else {
        	 if(m_file.readStringFile(_context, fileName) != null){
        		 String stringToDisplay = m_file.readStringFile(_context, fileName);
        		 text.setText(stringToDisplay);
        	 }
        	 else{
        		 return;
        	 }
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
				
				// my handler.  Handles the return info
				final Handler JsonHandler = new Handler(){

					@Override
					public void handleMessage(Message message){
						
						// what gets returned from the called service
						Object returnedObject = message.obj;
						
						// casting the object to a string
						String returnedObjectString = returnedObject.toString();
						
						if(message.arg1 == RESULT_OK && returnedObject != null){
							
							// calls on my FileManager class
					        m_file = FileManager.getInstance();
					        m_file.writeStringFile(_context, fileName, returnedObjectString);
							
							text.setText(m_file.readStringFile(_context, fileName));
							
							Log.i("object", returnedObjectString);
						}
					}
		    		
		    	}; 
				
		    	
		    	
		    	
				
				// creation of my messenger to the service
		    	Messenger jsonMessenger = new Messenger(JsonHandler);
				
				Intent myServiceIntent = new Intent(_context, JsonService.class);
				
				// basically this passes info to my service
				myServiceIntent.putExtra("messenger", jsonMessenger);
				myServiceIntent.putExtra("key", inputString);
				startService(myServiceIntent);
			}
		});
        
        
    }
    
    public void displayData(){
    	// loading my file into a string
    	String JSONString = m_file.readStringFile(this, fileName);
    	
    	ArrayList<HashMap<String, String>>mylist = new ArrayList<HashMap<String,String>>();
    	JSONObject job = null;
    	JSONArray results = null;
    	
    	try{
    		
    		// getting the array from the field "results"
    		results = job.getJSONArray("results");
    	} catch(Exception e){
    		
    	}
    	
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}