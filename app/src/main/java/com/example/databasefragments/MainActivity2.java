package com.example.databasefragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity2 extends AppCompatActivity {

    ViewPager2 viewPager;
    MyViewPagerAdapter myAdapter;
    TabLayout  tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TabLayout tabLayout = findViewById(R.id.tabLayout);


        viewPager = findViewById(R.id.viewPager2);
        myAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(),
                getLifecycle());
        myAdapter.addFragment(new fragment1());
        myAdapter.addFragment(new fragment2());
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(myAdapter);

        new TabLayoutMediator(
                tabLayout,
                viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Tab " + (position + 1));

                    }
                }
        ).attach();
    }
}