package com.example.rymasheuski.valery.vrpizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        UiUtil.prepareToolbar(this, true, R.string.about_us_title);
    }
}
