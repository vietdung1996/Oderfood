package com.vietdung.oderfood.ui.fooddetails.comment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.vietdung.oderfood.R;
import com.vietdung.oderfood.common.Common;
import com.vietdung.oderfood.model.ObjectClass.Comment;
import com.vietdung.oderfood.remote.APIOderFood;

public class AddCommentActivity extends AppCompatActivity implements CommentContract.View, RatingBar.OnRatingBarChangeListener {
    private RatingBar mRatingBar;
    private TextInputLayout mTextInputTitle;
    private TextInputLayout mTextInputComment;
    private EditText mEditComment;
    private EditText mEditTitle;
    private Button mButtonAgree;
    private CommentContract.Presenter mPresenter;
    private APIOderFood mAPIOderFood;

    private int masp = 0;
    private int star = 0;
    private String mIdevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        initView();
        initPermission();
        getInfor();
    }

    private void getInfor() {
        masp = getIntent().getIntExtra("IdFood", 0);
        mButtonAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;
                }
                mIdevice = telephonyManager.getDeviceId();
                String title = mEditTitle.getText().toString();
                String comment = mEditComment.getText().toString();
                String name = Build.MODEL;

                mPresenter.addComment(new Comment(mIdevice+masp, masp, name, title, comment, star));
                finish();
            }
        });
    }


    private void initView() {
        mRatingBar = findViewById(R.id.rating_bar_comment);
        mButtonAgree = findViewById(R.id.button_agree);
        mEditComment = findViewById(R.id.edit_comment);
        mEditTitle = findViewById(R.id.edit_title);
        mTextInputComment = findViewById(R.id.text_input_comment);
        mTextInputTitle = findViewById(R.id.text_input_title);
        mAPIOderFood = Common.getAPI();
        mPresenter = new PresenterComment(mAPIOderFood, this);
        mRatingBar.setOnRatingBarChangeListener(this);
    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_PHONE_STATE)) {

                } else {

                }
                //Register permission
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            } else {

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void addSuccess() {
        Toast.makeText(getApplicationContext(), "Cảm ơn vì đánh giá của bạn", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addFailure() {
        Toast.makeText(getApplicationContext(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        star = (int) v;
    }
}
