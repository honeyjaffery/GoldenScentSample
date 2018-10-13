package com.sample.example.goldenscentsample;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();
        // Tab for tab1
        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab1");
        // setting Title and Icon for the Tab
        spec1.setIndicator("Tab1");
        Intent Intent1 = new Intent(this, ShoppingActivity.class);
        spec1.setContent(Intent1);
        // Adding all TabSpec to TabHost
        tabHost.addTab(spec1); // Adding tab1
    }

}