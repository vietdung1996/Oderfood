package com.vietdung.oderfood.ui.home;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.ViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    private NavigationView mNavigationView;
    private Toolbar mToolbarHome;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        setToolBar();
    }

    private void setToolBar() {
        setSupportActionBar(mToolbarHome);
        getSupportActionBar();
        mToolbarHome.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        mToolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }


    private void initView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mNavigationView = findViewById(R.id.navigation_view);
        mToolbarHome = findViewById(R.id.tb_home);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        View headerLayout = mNavigationView.getHeaderView(0);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
