package com.Cory.week_3_final_project;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.lib.WebInfo;
import com.Cory.week_3_final_project.JsonService;
import com.Cory.week_3_final_project.R;
import com.Cory.week_3_final_project.SaveData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
    String bandFileName = "band_file_name.txt";
    
    ListView listView;
	
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
		
         
      // targetting my listView
         listView = (ListView)this.findViewById(R.id.list);
         View listHeader = this.getLayoutInflater().inflate(R.layout.list_header, null);
         listView.addHeaderView(listHeader);
         
		// getting the user information through the getIntent method
		Bundle data = getIntent().getExtras();
		if(data != null){
			String newString = data.getString("userInput");
			
			final Handler JsonHandler = new Handler(){
				
				@Override
				public void handleMessage(Message message){
					
					// what gets returned from the called service
					Object returnedObject = message.obj;
					
					// casting the object to a string
					String returnedObjectString = returnedObject.toString();
					
					if(message.arg1 == RESULT_OK && returnedObject != null){
						
						// calls on my FileManager class
				        m_file = SaveData.getInstance();
				        //m_file.writeStringFile(_context, fileName, returnedObjectString);
				        
				        // writting the exsisting band to a different file
				        m_file.writeStringFile(_context, bandFileName, returnedObjectString);
				        
				        // trying to append the object to the previous one to create a list
				        if(m_file.readStringFile(_context, fileName) != null){
				        	String newStoredString = m_file.readStringFile(_context, fileName) + " " + returnedObjectString;
				        	
				        	// if this works, we need to store newStoredString
				        	Log.i("combined String", "" + newStoredString);
				        	
				        	// overwritting the exsisting file
				        	m_file.writeStringFile(_context, fileName, newStoredString);
				        }
				        else{
				        	m_file.writeStringFile(_context, fileName, returnedObjectString);
				        }
						
						//text.setText(m_file.readStringFile(_context, fileName));
						
						//Log.i("object", returnedObjectString);
				        
				        displayData();
					}
				}

				
			};
			
			// creation of my messenger to the service
	    	Messenger jsonMessenger = new Messenger(JsonHandler);
			
			Intent myServiceIntent = new Intent(_context, JsonService.class);
			
			// basically this passes info to my service
			myServiceIntent.putExtra("messenger", jsonMessenger);
			myServiceIntent.putExtra("key", newString);
			startService(myServiceIntent);
			
			
		
			// this is the output section
			topTextBox = (TextView)findViewById(R.id.topText);
			topTextBox.setText("Here are the results for " + newString);
			
			//mainTextBox = (TextView)findViewById(R.id.mainText);
			
			Log.i("user", "" + newString);
		}
	}
	
	public void displayData(){
    	// loading my file into a string
    	String JSONString = m_file.readStringFile(this, bandFileName);
    	
    	ArrayList<HashMap<String, String>>mylist = new ArrayList<HashMap<String,String>>();
    	JSONObject job = null;
    	JSONArray results = null;
    	
    	try{
    		
    		// getting the array from the field "results"
    		job = new JSONObject(JSONString);
    		results = job.getJSONArray("results");
    		
    		// gathers the specific fields
    		String artistName = results.getJSONObject(0).getString("artistName").toString();
    		String artistGenre = results.getJSONObject(0).getString("primaryGenreName").toString();
    		String artistURL = results.getJSONObject(0).getString("artistLinkUrl").toString();
    		
    		//text.setText("artistName: " + artistName + "artistGenre: " + artistGenre + "artistURL: " + artistURL);
    		HashMap<String, String> displayMap = new HashMap<String, String>();
    		displayMap.put("artist", artistName);
    		displayMap.put("genre", artistGenre);
    		displayMap.put("url", artistURL);
    		
    		//displayMap.put("artist", cursor.getString(1));
    		
    		mylist.add(displayMap);
    		
    		// this is complicated but it basically assigns the rows for each element
    		SimpleAdapter adapter = new SimpleAdapter(this, mylist, R.layout.list_row, 
    				new String[] {"artist", "genre", "url"}, 
    				new int[] {R.id.artist, R.id.genre, R.id.url});
    		
    		listView.setAdapter(adapter);
    		
    	} catch(Exception e){
    		
    	}
    	
	}
}
