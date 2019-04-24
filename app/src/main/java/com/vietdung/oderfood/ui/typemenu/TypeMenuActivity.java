package com.vietdung.oderfood.ui.typemenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.TypeFoodAdapter;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.TypeFood;
import com.vietdung.oderfood.remote.APIOderFood;

import java.util.ArrayList;
import java.util.List;

public class TypeMenuActivity extends AppCompatActivity implements PresenterMenu.View {
    private List<TypeFood> mTypeFoods;
    private TypeFoodAdapter mTypeFoodAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbarMenu;
    private PresenterLogicMenu mPresenterLogicMenu;
    private APIOderFood mAPIOderFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_menu);

        initView();
        addEvents();
    }

    private void addEvents() {
        actionToolbar();


    }

    private void actionToolbar() {
        setSupportActionBar(mToolbarMenu);
        getSupportActionBar().setTitle("Type Menu");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_type_menu);
        mToolbarMenu = findViewById(R.id.toolbar_menu);
        mTypeFoods = new ArrayList<>();
        mTypeFoodAdapter = new TypeFoodAdapter(TypeMenuActivity.this,mTypeFoods);
        mAPIOderFood = Common.getAPI();
        mPresenterLogicMenu = new PresenterLogicMenu(this,mAPIOderFood);
        mRecyclerView.setAdapter(mTypeFoodAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        mPresenterLogicMenu.loadTypeFood();
    }

    @Override
    public void disPlayList(List<TypeFood> typeFoods) {
        mTypeFoods =  typeFoods;
        mTypeFoodAdapter.setTypeFoods(mTypeFoods);
       // Log.d("danhsach", "addEvents: "+mTypeFoods.get(1).getName());
    }
}
