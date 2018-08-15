package com.example.rymasheuski.valery.vrpizza.util;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.rymasheuski.valery.vrpizza.R;

/**
 * Created by valery on 14.8.18.
 */

public class UiUtil {

    private static final int NO_SUBTITLE_ID = 0;


    public static void prepareToolbar(AppCompatActivity activity, boolean useDefaultHomeAsUpIndicator, int subtitleResId){
        Toolbar toolbar = activity.findViewById(R.id.toolbar_main);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(!useDefaultHomeAsUpIndicator) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }

        if(subtitleResId != NO_SUBTITLE_ID) {
            toolbar.setSubtitle(subtitleResId);
        }
    }

    public static void prepareToolbar(AppCompatActivity activity){
       prepareToolbar(activity, false, NO_SUBTITLE_ID);
    }
}
