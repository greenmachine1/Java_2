package com.Cory.week_3_final_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// button for my band info retrieval
		Button bandInfoButton = (Button)findViewById(R.id.bandInfo);
		bandInfoButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// setting up the intent for the next activity
				Intent myIntent = new Intent(v.getContext(), BandInfo.class);
				
				// this will pass data to my activity (key, value)
				myIntent.putExtra("userInput", "yes");
				
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
