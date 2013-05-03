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
 * CalculateActivity 
 * This Activity is where file downloading
 */
package sim7.speedscanadvanced;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalculateActivity extends Activity {

	private long firstDownloadTime;// system time one
	private long secondDownloadTime;// system time second
	private int num;// holds the progressbar status
	Button btnStartProgress;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();

// method that gives the current value to progressBarStatus	
	public int getNum(int theNum){
		num= theNum;
		return num;
	}
// method to capture the 1st system time
	public long getTime1(){
		firstDownloadTime = System.currentTimeMillis();
		return firstDownloadTime;
	}
// method to capture the 2nd system time	
	public long getTime2(){
		secondDownloadTime = System.currentTimeMillis();
		return secondDownloadTime;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculate);
		addListenerOnButton();
		setupMessageButton();  
	}    

	private void setupMessageButton(){
	// get a reference to the button
		Button messageButton = (Button)findViewById(R.id.buttonSecond);
	
		// set the click listener to run my code
		View.OnClickListener myListener = new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
			long array[]= {firstDownloadTime, secondDownloadTime}; // gives times values to array
			//on click go to next Activity
			Intent i = new Intent(CalculateActivity.this, ResultsActivity.class);
			i.putExtra("times", array);
			startActivity(i);
			}	
		};	
		
		messageButton.setOnClickListener(myListener);
	}
		public void addListenerOnButton() {
			 
			btnStartProgress = (Button) findViewById(R.id.btnStartProgress);
			btnStartProgress.setOnClickListener(
	                 new OnClickListener() {
	 
			   @Override
			   public void onClick(View v) {
		   // sets up the progress bar
			progressBar = new ProgressDialog(v.getContext());
			progressBar.setCancelable(true);
			progressBar.setMessage("File downloading ...");
			progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressBar.setProgress(0);
			progressBar.setMax(100);
			progressBar.show();
			//reset progress bar status
			progressBarStatus = 0;
 
			new Thread(new Runnable() {
				  public void run() {
					while (progressBarStatus < 100) {
	 
					  // process some tasks
					  progressBarStatus = barUpdate();
	 
					  // your computer is too fast, sleep 1 second
					  try {
						Thread.sleep(1000);
					  } catch (InterruptedException e) {
						e.printStackTrace();
					  }
	 
					  // Update the progress bar
					  progressBarHandler.post(new Runnable() {
						public void run() {
						  progressBar.setProgress(progressBarStatus);
						  
						}
					  });
					}
	 
					// ok, file is downloaded,
					if (progressBarStatus >= 100) {
	 
						// sleep 2 seconds, so that you can see the 100%
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	 
						// close the progress bar dialog
						progressBar.dismiss();
					}
				  }
			       }).start();
			   }
			   
	                 }); 	
					
			
		// execute this when the downloader must be fired
			DownloadFile downloadFile = new DownloadFile();
			// link of the download file is placed below
    		downloadFile.execute("http://down.speedscan.3owl.com/Wildlife_Trimmed.zip");
 		
	}

		
       public int barUpdate() {

	     return num; 
	     }   		

	//	 code in DownloadFile was borrowed from online and now modified
      // under fair use license by the GPL license
       
	public class DownloadFile extends AsyncTask<String, Integer, String> {
	    @Override
	    protected String doInBackground(String... sUrl) {
	        try {
	            URL url = new URL(sUrl[0]);
	            URLConnection connection = url.openConnection();
	            connection.connect();
	            // this will be useful so that you can show a typical 0-100% progress bar
	            int fileLength = connection.getContentLength();
	        
	            // download in sd card
	            File root = android.os.Environment.getExternalStorageDirectory();               
	            // if the download folder does not exist, this will create it.	
	            File dir = new File (root.getAbsolutePath() + "/download");
	            if(dir.exists()==false) {
	                 dir.mkdirs();
	            }
	            
	            // download the file
	            String targetFileName = "al.jpg";
	            InputStream input = new BufferedInputStream(url.openStream());
	            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory() + "/download/" + targetFileName);
	            
	            byte data[] = new byte[1024];
	            long total = 0;
	            int count;
	            getTime1();
	            
	            while ((count = input.read(data)) != -1) {
	                total += count;
	                // publishing the progress....
	               int temp= (int) (total * 100 / fileLength);
	                	 getNum(temp);
	                output.write(data, 0, count);
	            }
	            
	            getTime2();        
	            output.flush();
	            output.close();
	            input.close();
	        } catch (Exception e) {
	        }
	        return null;
	    }
	}
}
