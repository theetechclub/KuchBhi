package com.etechclub.kuchbhi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    Button search_b;
    ListView search_lv;
    List<String> l;

    private boolean check_before_clicked_on_searchButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_b = (Button) findViewById(R.id.button);
        search_lv = (ListView) findViewById(R.id.search_listView);

        search_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Starts", "Starting");
                Toast.makeText(getApplicationContext(),"searching device...",Toast.LENGTH_SHORT).show();
                Log.e("Starts", "Toasted");

                //To display the item listed in R.array use the following given method

                String [] string = getResources().getStringArray(R.array.search_list);
                l = Arrays.asList(string);
                Log.e("Starts","Listed");
                search_lv.setAdapter(new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, l)); // Direct `string` can be used instead of `l`
                Log.e("Starts", "Created");
                check_before_clicked_on_searchButton = true;
            }
        });
        search_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(check_before_clicked_on_searchButton){
                    Toast.makeText(getApplicationContext(),"Device Selected : "
                            + search_lv.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
