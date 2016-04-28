package com.etechclub.kuchbhi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main22Activity extends AppCompatActivity {

    Button btn;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        btn = (Button)findViewById(R.id.btnSetIP);
        editText = (EditText)findViewById(R.id.editTextSetIP);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeforeControlPanel.ip=editText.getText().toString();
                Intent intent = new Intent("com.etechclub.kuchbhi.BEFORECONTROLPANEL");
                startActivity(intent);
            }
        });

    }
}
