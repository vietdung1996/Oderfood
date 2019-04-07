package com.vietdung.oderfood.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.ViewPagerAdapter;
import com.vietdung.oderfood.ui.cart.CartActivity;
import com.vietdung.oderfood.ui.fooddetails.PresenterLoginFoodDetails;
import com.vietdung.oderfood.ui.search.SearchActivity;
import com.vietdung.oderfood.ui.typemenu.TypeMenuActivity;

public class HomeActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener,
        NavigationView.OnNavigationItemSelectedListener {
    private NavigationView mNavigationView;
    private Toolbar mToolbarHome;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private TextView mTextCartSize;
    private Menu mMenu;
    private PresenterLoginFoodDetails mPresenterLoginFoodDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem itemCart = menu.findItem(R.id.it_cart);
        View customCart = MenuItemCompat.getActionView(itemCart);
        customCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        mTextCartSize = customCart.findViewById(R.id.text_count_item_cart);
        mTextCartSize.setText(String.valueOf(mPresenterLoginFoodDetails.countItemCart(getApplicationContext())));
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_notification:
                break;
            case R.id.it_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mTextCartSize.setText(String.valueOf(mPresenterLoginFoodDetails.countItemCart(getApplicationContext())));
    }

    private void addEvents() {
        setToolBar();
    }

    private void setToolBar() {
        setSupportActionBar(mToolbarHome);
        getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        mAppBarLayout = findViewById(R.id.app_bar_layout);
        mCollapsingToolbarLayout = findViewById(R.id.collap_toolbar);
        mAppBarLayout.addOnOffsetChangedListener(this);
        mNavigationView.setNavigationItemSelectedListener(this);
        mPresenterLoginFoodDetails = new PresenterLoginFoodDetails();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mCollapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(mCollapsingToolbarLayout)) {
            LinearLayout linearLayout = mAppBarLayout.findViewById(R.id.ln_search);
            linearLayout.animate().alpha(0).setDuration(100);

            MenuItem itSearch = mMenu.findItem(R.id.it_search);
            itSearch.setVisible(true);

        } else {
            LinearLayout linearLayout = mAppBarLayout.findViewById(R.id.ln_search);
            linearLayout.animate().alpha(1).setDuration(2000);
            try {
                MenuItem itSearch = mMenu.findItem(R.id.it_search);
                itSearch.setVisible(false);
            }catch (Exception e){

            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_menu:
                Intent intent = new Intent(HomeActivity.this, TypeMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_history:
                break;
            case R.id.nav_user:
                break;
        }
        return false;
    }
}
