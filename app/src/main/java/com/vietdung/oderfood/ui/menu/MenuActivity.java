package com.vietdung.oderfood.ui.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vietdung.oderfood.R;

public class MenuActivity extends AppCompatActivity {
    private Toolbar mToolbarMenu;
    private int mIdTypeFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initView();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        setToolbar();
        getTypeFoodID();
    }

    private void getTypeFoodID() {
        mIdTypeFood = getIntent().getIntExtra("idtypefood",-1);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbarMenu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Menu");
        mToolbarMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mToolbarMenu = findViewById(R.id.toolbar_menu);
    }
}
