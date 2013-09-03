package com.example.week_1_project;

import com.example.lib.WebInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // creating my global variables
        Context _context;
        Boolean _connected = false;
        
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
				// TODO Auto-generated method stub
				Log.i("yes", "Yes");
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
