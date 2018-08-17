package com.example.rymasheuski.valery.vrpizza.component;

import android.app.Activity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;



/**
 * Created by valery on 16.8.18.
 */

public class SpinnerComponent {

    private Spinner mSpinner;

    private int ids[];

    public SpinnerComponent(Activity activity, int spinnerId, int labelsArrayResId, int valuesArrayResId) {
        mSpinner = activity.findViewById(spinnerId);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(activity, labelsArrayResId,
                android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arrayAdapter);

        ids = activity.getResources().getIntArray(valuesArrayResId);


    }

    public Spinner getSpinner() {
        return mSpinner;
    }

    public Integer getSelectedItemId(){
        int position = mSpinner.getSelectedItemPosition();
        if(position > -1){
            return ids[position];
        }
        return null;
    }
}
