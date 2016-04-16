package com.etechclub.kuchbhi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SocketAddress add = new InetSocketAddress("192.168.192.1",8080);

        Socket skt = new Socket();
        boolean connect=true;
        try {
            skt.connect(add,10000);
        } catch (IOException e) {
            connect=false;
            e.printStackTrace();
        }finally {
            try {
                skt.close();
            } catch (IOException ex) {
                // feel free to do something moderately useful here, eg log the event
            }
        }
        TextView tv = (TextView) findViewById(R.id.sanple_textView4);
        tv.setText(connect?"yes":"no not done");
    }
}
