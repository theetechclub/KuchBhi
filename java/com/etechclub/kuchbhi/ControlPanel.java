package com.etechclub.kuchbhi;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

/**
 * Created by Dont Know on 19-Apr-16.
 * Updated 29-Apr-16 by Dont Know
 */
public class ControlPanel extends Activity {

    public static String ip = "192.168.192.99:8080";
    public static int pin = 66;

    int h, m;

    Button on, off, setTime;
    TimePicker timePicker;
    CheckBox cOn, cOff;
    String todo= "OFF";

    InputStream is = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controlpanel);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        on = (Button) findViewById(R.id.btnOn);
        off = (Button) findViewById(R.id.btnOff);
        setTime = (Button) findViewById(R.id.setTime);
        cOn = (CheckBox) findViewById(R.id.checkBoxOn);
        cOff = (CheckBox) findViewById(R.id.checkBoxOff);
        Toast.makeText(getApplicationContext(),ip,Toast.LENGTH_LONG).show();
        TextView textView = (TextView)findViewById(R.id.textView4);
        textView.append(" "+pin);
        Log.e("Control Panel", "Before on");
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Control Panel", "Inside On");
                Toast.makeText(getApplicationContext(),turn("task=ON&pin="+pin)?"Success":"Failed",
                        Toast.LENGTH_LONG).show();
            }
        });
        Log.e("Control Panel", "Before OFF");
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Control Panel", "Inside Off");
                Toast.makeText(getApplicationContext(),turn("task=OFF&pin="+pin)?"Success":"Failed",
                        Toast.LENGTH_LONG).show();
            }
        });

        Log.e("Control Panel", "Before setTime");
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                h = hourOfDay;
                m = minute;
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "" + h + " : " + m +
                        (turn("task="+todo+"&p="+pin+"&setwettime=YO&hh="+h+"&mm="+m) ? " Success" : " Failed"),
                        Toast.LENGTH_LONG).show();
            }
        });
        Log.e("Control Panel", "After set time");
        cOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cOff.setChecked(false);
                todo="ON";
            }
        });
        cOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cOn.setChecked(false);
                todo="OFF";
            }
        });
    }

    boolean turn(String s) {
        boolean check = true;
        try {
            /*Log.e("Pass 1.0.1", "Going in big nap");
            Thread.sleep(20000);
            Log.e("Pass 1.0.1", "Aaah that was a real big nap....");*/
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            Log.e("Pass 1.0", "Line 1 Executed");
            StrictMode.setThreadPolicy(policy);
            Log.e("Pass 1.0", "Line 2 Executed");
            @SuppressWarnings("deprecation") HttpClient httpclient = new DefaultHttpClient();
            Log.e("Pass 1.0", "httpclient created");
            @SuppressWarnings("deprecation") HttpPost httppost = new HttpPost(
                    "http://"+ip+"/php_project/task.php/?"+s);
            Log.e("Pass 1.0", "Link setup done");
            HttpResponse response = httpclient.execute(httppost);
            Log.e("Pass 1.0", "Link executed");
            HttpEntity entity = response.getEntity();
            Log.e("Pass 1.0", "Entity setup success");
            is = entity.getContent();
            check = true;
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            check = false;
        } finally{
            return check;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
