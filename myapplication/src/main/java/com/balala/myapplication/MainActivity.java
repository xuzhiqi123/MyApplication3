package com.balala.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import com.balala.myapplication.activity.FrogetpasswardActivity;
import com.balala.myapplication.activity.RegisterActivity;
import com.example.myapplication.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView mEdit;
    private TextView mRegister;
    private ImageView mWatch;
    private ImageView mWechat;
    private Button mActivityGo;
    private TextView mFroget;
    private EditText mEt1;
    private EditText mEt2;
    // AAppID：wx24009bcc9adc6318
    private static final String APP_ID = "wx24009bcc9adc6318";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;


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
        mEt1 = findViewById(R.id.et1);
        mEt2 = findViewById(R.id.et2);
        initView();
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
                // 微信登陆方法
                initWechat();
                // 微信登陆授权方法
                initSou();


            }
        });
        mWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mWatch.isClickable();
                if (mWatch.isClickable()) {
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

    private void initSou() {

        if (!api.isWXAppInstalled()) {
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }

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

    // 微信登陆
    private void initWechat() {

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
//初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
//        textObj.text = "";

//用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
//        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());  //transaction字段用与唯一标示一个请求
        req.message = msg;
//        req.scene = mTargetScene;

//调用api接口，发送数据到微信
        api.sendReq(req);
    }

}
