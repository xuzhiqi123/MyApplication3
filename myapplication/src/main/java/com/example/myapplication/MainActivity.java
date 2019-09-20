package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.myapplication.activity.FrogetpasswardActivity;
import com.example.myapplication.activity.RegisterActivity;
import com.example.myapplication.activity.WXEntryActivity;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView mEdit;
    private TextView mRegister;
    private ImageView mWatch;
    private ImageView mWechat;
    private Button mActivityGo;
    private TextView mFroget;
    private EditText mEt1;
    private EditText mEt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit = findViewById(R.id.edit);
        mRegister = findViewById(R.id.register);
        mWatch = findViewById(R.id.watch);
        mWechat = findViewById(R.id.wechat);
        mEdit = findViewById(R.id.edit);
        mRegister = findViewById(R.id.register);
        mWatch = findViewById(R.id.watch);
        mActivityGo = findViewById(R.id.activity_go);
        mWechat = findViewById(R.id.wechat);
        mFroget = findViewById(R.id.froget);
        initView();
        mEt1 = findViewById(R.id.et1);
        mEt2 = findViewById(R.id.et2);
    }

    private void initView() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        mFroget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FrogetpasswardActivity.class));
            }
        });
        mWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "微信登陆", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, WXEntryActivity.class));
            }
        });
        mWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mWatch.isClickable();
                if (mWatch.isClickable()){
                    mWatch.setClickable(true);
                }
                Toast.makeText(MainActivity.this, "查看密码", Toast.LENGTH_SHORT).show();
            }
        });

//        mActivityGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        new Intent(MainActivity.this,)
//            }
//        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //处理Editext的光标隐藏、显示逻辑
                    mEt1.clearFocus();
                    mEt2.clearFocus();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
