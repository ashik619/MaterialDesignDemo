package com.ashik619.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashik619.demo.custom_views.ChildFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.profileImageView)
    ImageView profileImageView;
    @BindView(R.id.backButton)
    ImageButton backButton;
    @BindView(R.id.searchButton)
    ImageButton searchButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapseToolbar;


    @BindView(R.id.callButton)
    ImageButton callButton;
    @BindView(R.id.editButton)
    ImageButton editButton;
    @BindView(R.id.slidingTablayout)
    TabLayout slidingTablayout;
    @BindView(R.id.container)
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    String phoneNumber = "9891345672";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        slidingTablayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        slidingTablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        slidingTablayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.textColorLight),
                ContextCompat.getColor(getApplicationContext(), R.color.textColorDark));
        slidingTablayout.setupWithViewPager(viewPager);
        viewPagerAdapter.notifyDataSetChanged();
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCallPermission();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Edit Toast",
                        Toast.LENGTH_LONG).show();

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search Toast",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            // Returns the number of tabs
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            // Returns a new instance of the fragment
            ChildFragment childFragment = new ChildFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            childFragment.setArguments(bundle);

            return childFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PROFILE";
                case 1:
                    return "CONTACT";
                case 2:
                    return "PHONE";
                case 3:
                    return "ADDRESS";
            }
            return null;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    void checkCallPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
            }else makeCall();
        }else makeCall();
    }
    void makeCall(){
        Log.d("call","starting");
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }
    void requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall();
                } else {
                    requestPermission();
                }

            }

        }
    }
}
