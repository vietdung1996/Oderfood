package com.vietdung.oderfood.ui.fooddetails;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.adapter.DetailPagerAdapter;
import com.vietdung.oderfood.adapter.FoodAdapter;
import com.vietdung.oderfood.model.ObjectClass.Food;
import com.vietdung.oderfood.model.cart.ModelCart;
import com.vietdung.oderfood.ui.fooddetails.inforfragment.FragmentInformation;
import com.vietdung.oderfood.ui.fooddetails.reviewfragment.FragmentReview;

import java.io.ByteArrayOutputStream;

public class FoodDetailsActivity extends AppCompatActivity implements View.OnClickListener,
        PresenterFoodDatails.View {
    private static final int PAGE_LIMIT = 2;
    public static Food mFood;
    private ImageView mImageFood;
    private Toolbar mToolbar;
    private FloatingActionButton mActionButtonFavorite;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mButtonAddCart;
    private PresenterLoginFoodDetails mPresenter;
    private ModelCart mModelCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInforFood();
        setContentView(R.layout.activity_food_details);
        initView();
        addEvents();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_cart:
                Bitmap bitmap = ((BitmapDrawable) mImageFood.getDrawable()).getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] imageFoodCart = outputStream.toByteArray();
                mFood.setImageCart(imageFoodCart);
                mFood.setQuality(1);
                mPresenter.addCart(mFood, getApplicationContext());

//                CustomDialogSheet customDialogSheet = CustomDialogSheet.newInstance(mFood);
//                customDialogSheet.show(getSupportFragmentManager(), "custom sheet");
                //startActivity(CartActivity.getCartIntent(mFood));
                break;
        }

    }

    private void addEvents() {
        setToolbar();
        setupViewPager();
    }

    private void setupViewPager() {
        DetailPagerAdapter pagerAdapter = new DetailPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new FragmentInformation(), "Thông tin");
        pagerAdapter.addFragment(new FragmentReview(), "Đánh giá");
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(PAGE_LIMIT);
    }

    private void getInforFood() {
        Bundle b = getIntent().getBundleExtra(FoodAdapter.INTENT_KEY);
        mFood = b.getParcelable(FoodAdapter.PARA_KEY);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void initView() {
        mImageFood = findViewById(R.id.image_food_details);
        mToolbar = findViewById(R.id.toolbar_details);
        mTabLayout = findViewById(R.id.tab_layout_details);
        mViewPager = findViewById(R.id.view_pager_details);
        mActionButtonFavorite = findViewById(R.id.float_button_favorite);
        mButtonAddCart = findViewById(R.id.btn_add_cart);
        mButtonAddCart.setOnClickListener(this);
        Glide.with(getApplicationContext()).load(mFood.getImage()).into(mImageFood);
        mPresenter = new PresenterLoginFoodDetails(this);
    }

    @Override
    public void addCartSuccess() {
        CustomDialogSheet customDialogSheet = CustomDialogSheet.newInstance(mFood);
        customDialogSheet.show(getSupportFragmentManager(), "custom sheet");
    }

    @Override
    public void addCartFailure() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }
}
