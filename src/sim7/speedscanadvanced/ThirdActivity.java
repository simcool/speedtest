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
		double speedTime= (1154856.0*8.0)/finalTime; 
				
		t=(TextView)findViewById(R.id.textViewResults);
	//	CalculateActivity.getTime1();
		
		
		System.out.println("first download time=" +arrayB[0]);
		System.out.println("second download time=" +arrayB[1]);
		System.out.println("difference milli time=" +finalMilTime);
		System.out.println("difference double milli time=" +finalTime);
		t.setText("Speed Test Results= " + df.format(speedTime) +" kbps");
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

}
