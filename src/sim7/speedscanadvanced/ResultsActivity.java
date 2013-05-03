package sim7.speedscanadvanced;

import java.text.DecimalFormat;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
public class ResultsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		
		TextView t =new TextView(this); // initiates 2 TextView objects
		TextView t2 =new TextView(this); 
		// sets the textview objects to their respective xml textviews
		t=(TextView)findViewById(R.id.textViewResults);
		t2=(TextView)findViewById(R.id.textViewMore);
		//initiates the a Decimal format object
		DecimalFormat dFormatObject = new DecimalFormat("#.##");
		
		// gets the two system times from Calculate Activity
		Bundle extrasObj = getIntent().getExtras();
		long[] arrayB = extrasObj.getLongArray("times");
		// gets the amount of time it takes for the download complete in milliseconds
		long finalMilTime =arrayB[1] - arrayB[0];
		double finalTime=  finalMilTime;	// sets the time as a double variable
		double speedTime= (3799551.0*8.0)/finalTime; // calculates results in kbps

// text that displays all the results
		t.setText("Speed Test Results= \n"+
				"Current Download speed \n"+ dFormatObject.format(speedTime) +" kbps \n"
				+dFormatObject.format(speedTime/1000.0) +" mbps");

		t2.setText("This will download a 250 Megabyte file in " +dFormatObject.format(250.0/(speedTime/8192.0))	
				+	" seconds\n or " +dFormatObject.format((250.0/(speedTime/8192.0))/60.0) + " minutes.\n"+
				"\nThis will download a 1 Gigabyte file in " + dFormatObject.format(1.0/(speedTime/8388608.0)) + " seconds\n " +
				"or " + dFormatObject.format((1.0/(speedTime/8388608.0))/60.0) + " minutes");
	}
}