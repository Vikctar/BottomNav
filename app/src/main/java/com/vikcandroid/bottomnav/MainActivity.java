package com.vikcandroid.bottomnav;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_shop);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.navigation_shop:
                            actionBar.setTitle(R.string.title_shop);
                            return true;
                        case R.id.navigation_cart:
                            actionBar.setTitle(R.string.title_cart);
                            return true;
                        case R.id.navigation_profile:
                            actionBar.setTitle(R.string.title_profile);
                    }
                    return false;
                }
            };
}
