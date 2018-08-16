package com.example.rymasheuski.valery.vrpizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

public class OrderResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_result);

        UiUtil.prepareToolbar(this, true, R.string.order_result_title);
    }
}
