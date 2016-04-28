package com.etechclub.kuchbhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Dont Know on 28-Apr-16.
 */
public class BeforeControlPanel extends Activity{
    Button b1,b2,b3;
    public static String ip = "0.0.0.0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beforecontrolpanel);

        b1 = (Button)findViewById(R.id.beforeBtn1);
        b2 = (Button)findViewById(R.id.beforeBtn2);
        b3 = (Button)findViewById(R.id.beforeBtn3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlPanel.ip=ip;
                ControlPanel.pin=66;
                Intent intent = new Intent("com.etechclub.kuchbhi.CONTROLPANEL");
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlPanel.ip=ip;
                ControlPanel.pin=69;
                Intent intent = new Intent("com.etechclub.kuchbhi.CONTROLPANEL");
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlPanel.ip=ip;
                ControlPanel.pin=45;
                Intent intent = new Intent("com.etechclub.kuchbhi.CONTROLPANEL");
                startActivity(intent);
            }
        });

    }
}
