package com.example.rymasheuski.valery.vrpizza;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rymasheuski.valery.vrpizza.cart.ShoppingCartActivity;
import com.example.rymasheuski.valery.vrpizza.menu.FoodListFragment;
import com.example.rymasheuski.valery.vrpizza.model.FoodType;
import com.example.rymasheuski.valery.vrpizza.util.InjectionUtil;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private MainViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.pager_food_types);

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

        mViewModel = InjectionUtil.getViewModel(this, MainViewModel.class);
        mViewModel.init();

        FoodTypeFragmentPagerAdapter mFoodTypePagerAdapter = new FoodTypeFragmentPagerAdapter(getSupportFragmentManager(),
                getApplicationContext());
        mViewModel.getFoodTypes().observe(this, foodTypes -> mFoodTypePagerAdapter.replaceData(foodTypes));

        mViewPager.setAdapter(mFoodTypePagerAdapter);
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

        private List<FoodType> mFoodTypes;
        private Context mContext;


        FoodTypeFragmentPagerAdapter(FragmentManager fm, Context context)
        {
            super(fm);
            mContext = context;

        }


        public void replaceData(List<FoodType> foodTypes){
            mFoodTypes = foodTypes;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            int foodTypeId = mFoodTypes.get(position).getId();
            return FoodListFragment.newInstance(foodTypeId);


        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            if(mFoodTypes != null){
                return getStringByString(mFoodTypes.get(position).getNameKey());
            }else{
                return null;
            }
        }

        @Override
        public int getCount() {
            return mFoodTypes != null ? mFoodTypes.size() : 0;
        }


        public String getStringByString(String key) {
            String retString = key;
            int id = mContext.getResources().getIdentifier(key, "string", mContext.getPackageName());
            if (id != 0) {
                retString = mContext.getString(id);
            }

            return retString;
        }


    }

}
