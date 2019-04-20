package com.filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

public class ImagePreviewActivity extends AppCompatActivity {

     Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
//        tb= (Toolbar) findViewById(R.id.toolbar2);
//        tb.setTitle(getText(R.string.app_name));
//        tb.setNavigationIcon(R.drawable.icon);
//        tb.setTitleTextColor(getColor(R.color.white));
    }
}
