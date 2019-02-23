package com.vietdung.oderfood.ui.login;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.R;
import com.vietdung.oderfood.ui.signin.RegisterActivity;
import com.vietdung.oderfood.remote.APIOderFood;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewLogin {
    private EditText mEditEmail;
    private EditText mEditPassWord;
    private ImageView mImageFoodIcon;
    private TextView mTextOder;
    private ProgressBar mProgressLoading;
    private RelativeLayout rootView;
    private RelativeLayout afterAnimationView;
    private TextView mTextSignUp;
    private Button btnLogin;

    private APIOderFood mService;
    private PresenterLogicLogin mLogicLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextOder.setVisibility(GONE);
                mProgressLoading.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorSplashText));
                mImageFoodIcon.setImageResource(R.drawable.icon_oder);
                startAnimation();

            }
        }, 5000);
    }

    private void initView() {
        mEditEmail = findViewById(R.id.edit_emaill);
        mEditPassWord = findViewById(R.id.edit_password);
        mImageFoodIcon = findViewById(R.id.image_food_icon);
        mTextOder = findViewById(R.id.text_oder);
        mProgressLoading = findViewById(R.id.progress_loading);
        rootView = findViewById(R.id.root_view);
        afterAnimationView = findViewById(R.id.after_animation_view);
        mTextSignUp = findViewById(R.id.text_sign_up);
        mTextSignUp.setOnClickListener(this);
        mService = Common.getAPI();
        btnLogin = findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(this);
        mLogicLogin = new PresenterLogicLogin(this, mService);

    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = mImageFoodIcon.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                afterAnimationView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_sign_up:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.loginButton:
                //authenticateUser(mEditEmail.getText().toString(), mEditPassWord.getText().toString());
                mLogicLogin.authenticateUser(mEditEmail.getText().toString(), mEditPassWord.getText().toString());
                break;
        }
    }

    @Override
    public void LoginSuccess() {
        Toast.makeText(MainActivity.this, "Login Success !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginFail() {
        Toast.makeText(MainActivity.this, "Login Fail!", Toast.LENGTH_SHORT).show();
    }
}
