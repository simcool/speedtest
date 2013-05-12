package sim7.speedscanadvanced;
/*
 * Copyright 2013 Simeon Burns

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
/*
 * @author Simeon Burns
 * simcool8@yahoo.com
 * 
 * MainActivity 
 * This Activity is where the user initially starts
 */
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView t =new TextView(this); //creates a TextView Object
		t=(TextView)findViewById(R.id.textViewCalculate);
		// displays initial welcome message
		t.setText("Welcome to \nSpeedScan Advanced \n - the most informative Internet speed test " +
				"application. Press button below to run application.\n" +
				"You will need a sd card or external memory device for this program to work.\n");
		setupMessageButton();
	}

	private void setupMessageButton(){
	// get a reference to the button
		Button messageButton = (Button)findViewById(R.id.buttonMain);
	
		// set the click listener to run my code
		View.OnClickListener myListener = new View.OnClickListener(){
			
			// this method will send app to 2 intent- the Calculate Activity class
			@Override
			public void onClick(View v){
			startActivity(new Intent(MainActivity.this, CalculateActivity.class));
			}	
		};	
		// sets the on Click Listener
		messageButton.setOnClickListener(myListener);
	}
}
