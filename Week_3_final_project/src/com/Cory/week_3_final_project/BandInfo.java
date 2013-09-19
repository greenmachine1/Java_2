package com.Cory.week_3_final_project;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BandInfo extends Activity {
	
	TextView topTextBox;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bandinfo);
		
		// getting the user information through the getIntent method
		Bundle data = getIntent().getExtras();
		if(data != null){
			String newString = data.getString("userInput");
			
			topTextBox = (TextView)findViewById(R.id.topText);
			topTextBox.setText(newString);
			
			Log.i("user", "" + newString);
		}
	}
}
