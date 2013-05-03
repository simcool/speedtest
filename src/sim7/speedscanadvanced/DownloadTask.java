//package sim7.speedscanadvanced;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Environment;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class DownloadTask extends AsyncTask<Void, Void, Void>{
//
//	ProgressDialog pDialog;
//
//    protected void onPreExecute() {
//        
//
//    }
//
//    protected Void doInBackground(Void... unused) {
//
//    //put here what u want to try   
//		TextView tViewObject = new TextView(DownloadTask.this);
//        tViewObject.setText("This is download file program... ");
//
//        try {
//            //this is the file you want to download from the remote server
//            String path ="http://www.fullissue.com/wp-content/uploads/2010/12/Adam-Lambert.jpg";
//            //this is the name of the local file you will create
//
//            String targetFileName = "al.jpg";
//
//            boolean eof = false;
//
//            URL u = new URL(path);
//            HttpURLConnection c = (HttpURLConnection) u.openConnection();
//            c.setRequestMethod("GET");
//            c.setDoOutput(true);
//            c.connect();
//            
//            // my added code
//            File root = android.os.Environment.getExternalStorageDirectory();               
//
//            File dir = new File (root.getAbsolutePath() + "/download");
//            if(dir.exists()==false) {
//                 dir.mkdirs();
//            }
//            //simeon's added code
//            
//            
//            String PATH_op = Environment.getExternalStorageDirectory() + "/download/" + targetFileName;
//
//            tViewObject.append("\nPath > " + PATH_op);
//
//            FileOutputStream f = new FileOutputStream(new File(PATH_op));
//
//            InputStream in = c.getInputStream();
//            byte[] buffer = new byte[1024];
//            int len1 = 0;
//            while ( (len1 = in.read(buffer)) > 0 ) {
//                f.write(buffer,0, len1);
//            }
//
//            f.close();
//
//            } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            } catch (ProtocolException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            Toast.makeText(DownloadTask.this, e.getMessage(), Toast.LENGTH_LONG).show();
//          //Toast.makeText(MainActivity.this, "You clicked it!", 	Toast.LENGTH_LONG).show();
//        }
//
//        tViewObject.append("\nAnother append!");
//        this.setContentView(tViewObject);
//    	
//        return (null);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        // TODO Auto-generated method stub
//        super.onProgressUpdate(values);
//    }
//
//    protected void onPostExecute(Void unused) {
//        pDialog.dismiss();
//    }
//
//}
//	
//	
//
