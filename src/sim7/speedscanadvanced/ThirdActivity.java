package sim7.speedscanadvanced;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		TextView t =new TextView(this); 
		   DecimalFormat df = new DecimalFormat("#.##");
	    ;
		Bundle extras = getIntent().getExtras();
		long[] arrayB = extras.getLongArray("numbers");
		long finalMilTime =arrayB[1] - arrayB[0];
		double finalTime=  finalMilTime;
		double speedTime= (3799551.0*8.0)/finalTime; 
				
		t=(TextView)findViewById(R.id.textViewResults);
	//	CalculateActivity.getTime1();
		TextView t2 =new TextView(this); 
		t2=(TextView)findViewById(R.id.textViewMore);
		System.out.println("first download time=" +arrayB[0]);
		System.out.println("second download time=" +arrayB[1]);
		System.out.println("difference milli time=" +finalMilTime);
		System.out.println("difference double milli time=" +finalTime);
		t.setText("Speed Test Results= \n"+
		"Current Download speed \n"+ df.format(speedTime) +" kbps \n"
		+df.format(speedTime/1000.0) +" mbps");
		
		t2.setText("This will download a 250 Megabyte file in " +df.format(250.0/(speedTime/8000.0))	
				+	" seconds\n or " +df.format((250.0/(speedTime/8000.0))/60.0) + "minutes."+
				"This will download a 1 Gigabyte file in " + df.format(250.0/(speedTime/8000.0)) + "seconds\n " +
						"or" + df.format((250.0/(speedTime/8000.0))/60.0));
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

}
