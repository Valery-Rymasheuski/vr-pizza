package com.example.rymasheuski.valery.vrpizza;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rymasheuski.valery.vrpizza.menu.FoodListFragment;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

public class MainActivity extends AppCompatActivity  {

    private FoodTypeFragmentPagerAdapter mFoodTypePagerAdapter;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.pager_food_types);
        String foodTabs[] = getResources().getStringArray(R.array.food_tabs);
        mFoodTypePagerAdapter = new FoodTypeFragmentPagerAdapter(getSupportFragmentManager(), foodTabs);
        mViewPager.setAdapter(mFoodTypePagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout_food_types);
        tabLayout.setupWithViewPager(mViewPager);

        UiUtil.prepareToolbar(this, false, R.string.menu_title);

        mDrawerLayout = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.drawer_navigation_view);
        navigationView.setNavigationItemSelectedListener( item -> {
            switch (item.getItemId()){
                case R.id.nav_news:
                    startActivity(NewsActivity.class);
                    break;
                case R.id.nav_delivery:
                    startActivity(DeliveryActivity.class);
                    break;
                case R.id.nav_about_us:
                    startActivity(AboutUsActivity.class);
                    break;
                }


            return true;

        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()){
                case R.id.action_food_menu:
                    startActivity(MainActivity.class);
                    break;

                case R.id.action_shopping_cart:
                    startActivity(ShoppingCartActivity.class);
                    break;



            }
            return true;

        });
    }

    private void startActivity(Class<?> intentClass){
        Intent intent = new Intent(getApplicationContext(), intentClass);
        startActivity(intent);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toolbar_shopping_cart:
                startActivity(ShoppingCartActivity.class);
                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    public static class FoodTypeFragmentPagerAdapter extends FragmentPagerAdapter {

        private String foodTabs[];


        public FoodTypeFragmentPagerAdapter(FragmentManager fm, String foodTabs[])
        {
            super(fm);
            this.foodTabs = foodTabs;
        }

        @Override
        public Fragment getItem(int position) {
            return FoodListFragment.newInstance(position);


        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            if(foodTabs != null){
                return foodTabs[position];
            }else{
                return null;
            }


        }

        @Override
        public int getCount() {
            return foodTabs != null ? foodTabs.length : 0;
        }
    }

}
