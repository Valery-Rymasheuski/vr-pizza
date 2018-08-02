package com.example.rymasheuski.valery.vrpizza;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.Pizza;

public class MainActivity extends AppCompatActivity  implements FoodTypesFragment.OnFragmentInteractionListener,
        FoodFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    public void onListFragmentInteraction(Food item) {

    }
}
