package com.vikcandroid.bottomnav.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vikcandroid.bottomnav.R;
import com.vikcandroid.bottomnav.fragment.CartFragment;
import com.vikcandroid.bottomnav.fragment.ProfileFragment;
import com.vikcandroid.bottomnav.fragment.StoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    private ActionBar actionBar;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        actionBar = getSupportActionBar();
//        actionBar.setTitle(R.string.title_shop);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // load the store fragment by default
        initFragment(new StoreFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_shop:
                            toolbar.setTitle(R.string.title_shop);
                            initFragment(new StoreFragment());
                            return true;
                        case R.id.navigation_cart:
                            toolbar.setTitle(R.string.title_cart);
                            initFragment(new CartFragment());
                            return true;
                        case R.id.navigation_profile:
                            toolbar.setTitle(R.string.title_profile);
                            initFragment(new ProfileFragment());
                    }
                    return false;
                }
            };

    private void initFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
