package com.etechclub.kuchbhi;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


public class Main2Activity extends AppCompatActivity implements Runnable {

    static boolean check = false;
    static TextView textView;

    InputStream is = null;
    /*String result = null;
    String line = null;*/

    private Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.e("Pass 0", "Before every thing");

        if(t == null) {
            t = new Thread(this, "Thread");
        }


        textView = (TextView) findViewById(R.id.sanple_textView4);

        Log.e("Pass 0", "Thread Created");
        this.start();
        Log.e("Pass 0", "Thread started");

    }

    public static void textt() {
        Log.e("Pass Run 0", "Going to start");
        textView.setText(check ? "Yo Done" : "No...Something went wrong");
        Log.e("Pass Run 0", "Text written");
    }

    @Override
    public void run() {
        check=this.checkConnection();
        Main2Activity.textt();
    }

    public void start(){
        t.start();
    }


    boolean checkConnection() {
        Log.e("Pass 1", "Starting All");
        Log.e("Pass 1", "Entry level list clear");
        boolean check = true;
        try {
            /*Log.e("Pass 1.0.1", "Going in big nap");
            Thread.sleep(5000);
            Log.e("Pass 1.0.1", "Aaah that was a real big nap....");*/
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            Log.e("Pass 1.0", "Line 1 Executed");
            StrictMode.setThreadPolicy(policy);
            Log.e("Pass 1.0", "Line 2 Executed");
            @SuppressWarnings("deprecation") HttpClient httpclient = new DefaultHttpClient();
            Log.e("Pass 1.0", "httpclient created");
            @SuppressWarnings("deprecation") HttpPost httppost = new HttpPost(
                    "http://192.168.192.1:8080");
            Log.e("Pass 1.0", "Link setup done");
            HttpResponse response = httpclient.execute(httppost);
            Log.e("Pass 1.0", "Link executed");
            HttpEntity entity = response.getEntity();
            Log.e("Pass 1.0", "Entity setup success");
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            check = false;
        }
        return check;
    }

}