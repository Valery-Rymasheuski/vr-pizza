package com.example.rymasheuski.valery.vrpizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

public class DeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        UiUtil.prepareToolbar(this, true, R.string.delivery_title);
    }
}
