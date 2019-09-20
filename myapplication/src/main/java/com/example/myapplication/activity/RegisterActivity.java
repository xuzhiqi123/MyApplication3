package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.bean.Identifying;
import com.example.myapplication.util.MyService;
import com.example.myapplication.util.TextWatcherUtil;
import com.example.myapplication.util.Utils;
import com.example.myapplication.webview.AboutyaofunActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "xzq";
    private ImageView mFrogetEdits;
    private TextView mRegister;
    private TextView mFrogetCode;
    private Button mFrogetYes;
    private InputMethodManager imm;
    private EditText et_phone;
    private EditText et_identifiing;
    private EditText et_password;
    private CheckBox mBox;
    private TimeCount time;
    private TextView mAbout;
    private TextView mRedSpeak;
    private String phone;
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        time = new TimeCount(60000, 1000);
        mFrogetEdits = findViewById(R.id.froget_edits);
        mRegister = findViewById(R.id.register);
        mFrogetCode = findViewById(R.id.get_code);
        mFrogetYes = findViewById(R.id.froget_yes);
        mFrogetEdits = findViewById(R.id.froget_edits);
        et_phone = findViewById(R.id.et_phone);
        et_identifiing = findViewById(R.id.et_identifiing);
        et_password = findViewById(R.id.et_password);
        mBox = findViewById(R.id.box);
        mAbout = findViewById(R.id.about);
        mRedSpeak = findViewById(R.id.red_speak);
        onView();

    }

    private void onView() {
        //跳转登陆页面
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });


        //取消（返回登陆页面）
        mFrogetEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });


        //点击 获取验证码 进行以下操作
        et_identifiing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比较手机号是否符合规则 符合规则弹出"获取验证码"并且验证码开始倒计时，红色文字隐藏
                String phoneNum = et_identifiing.getText().toString().trim();
                //切割字符串将空格清除
                String[] split = phoneNum.split(" ");
                //重新拼接手机号
                phone = split[0] + split[1] + split[2];
                Log.e("手机号码：", phone);
                if (phone.matches("^13[0-9]{1}[0-9]{8}$|15[0125689]{1}[0-9]{8}$|18[0-3,5-9]{1}[0-9]{8}$|17[0-3,5-9]{1}[0-9]{8}$|19[0-3,5-9]{1}[0-9]{8}$")) {
                    Toast.makeText(RegisterActivity.this, "获取验证码", Toast.LENGTH_SHORT).show();
                    time.start();
                    mRedSpeak.setVisibility(View.GONE);
                    Log.i(TAG, "手机号码 " + phone);
//                    initData();// 获取手机号码 返回后台进行解析
                    // 如果手机号码不符合规则 提示用户正确输入手机号 红色文字显示 字体变成"请输入正确的手机号码"
                    //
                } else {
                    Toast.makeText(RegisterActivity.this, " 请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    mRedSpeak.setVisibility(View.VISIBLE);
                    mRedSpeak.setText("请输入正确的手机号码");
                }


            }

        });


        // 验证手机号码
        TextWatcherUtil.addPhoneNumberTextWatcher(et_phone, new TextWatcherUtil.Callback() {
            @Override
            public void callback() {
                //todo edittext changed后回调
                initData();
            }
        });
        //验证密码
        TextWatcherUtil.addPasswordTextWatcher(et_password, new TextWatcherUtil.Callback() {
            @Override
            public void callback() {
                intiPasswordData();
            }
        });

        //跳转到关于要FUN页面
        mAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, AboutyaofunActivity.class));
            }
        });
    }

//
//    private void initCodeData() {
//        //验证码判断为空也不会报错
////        if (phone.length() <= 0 || phone.length() > 13) {
////            Toast.makeText(RegisterActivity.this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
////        }
//
//        //重新判断手机号是否规范 （matches 正则的规则）
//        if (phone.matches("^13[0-9]{1}[0-9]{8}$|15[0125689]{1}[0-9]{8}$|18[0-3,5-9]{1}[0-9]{8}$|17[0-3,5-9]{1}[0-9]{8}$|19[0-3,5-9]{1}[0-9]{8}$")) {
//            Toast.makeText(RegisterActivity.this, "获取验证码", Toast.LENGTH_SHORT).show();
//            time.start();
//            mRedSpeak.setVisibility(View.GONE);
//            Log.i(TAG, "手机号码 " + phone);
//
//            // 如果手机号码不符合规则 提示用户正确输入手机号 红色文字显示 字体变成"请输入正确的手机号码"
//            //
//        } else {
//            Toast.makeText(RegisterActivity.this, " 请输入正确的手机号码", Toast.LENGTH_SHORT).show();
//            mRedSpeak.setVisibility(View.VISIBLE);
//            mRedSpeak.setText("请输入正确的手机号码");
//        }
//        initData();// 获取手机号码 返回后台进行解析
//    }

    // 倒计时 验证码

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mFrogetCode.setBackgroundColor(Color.parseColor("#ffffff"));
            mFrogetCode.setClickable(false);
            mFrogetCode.setText("(" + millisUntilFinished / 1000 + "s" + ")重新获取");
        }

        @Override
        public void onFinish() {
            mFrogetCode.setText("重新发送");
            mFrogetCode.setClickable(true);
            mFrogetCode.setBackgroundColor(Color.parseColor("#ffffff"));
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
                    et_phone.clearFocus();
                    et_password.clearFocus();
                    et_identifiing.clearFocus();
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

    public class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.e("xuzhiqi", message);
        }
    }


    private OkHttpClient okhttpclient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .addNetworkInterceptor(logInterceptor)
                    .build();    // 解析没有成功 所以解不出来
        }    // 加载的内容出不来 这个可可可咋办 天啊 我今天算是啥啥都没干 真难 天啊 我咋啥都忘
        return mOkHttpClient;    //把手机号码 返回到后台解析 再把验证码发送到手机
    }

    private void initData() {
        Toast.makeText(this, "我这里是验证码发送到用户短信", Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.yaofun.vip/api/verification_code/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttpclient())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Identifying identifying = new Identifying();
        identifying.setUser_id("-1");
        identifying.setVersion("-1");
        identifying.setCurrent_device("android");
        Log.e("xuzhiqi", "initData: " + Utils.getNowDate());
        identifying.setRequest_start_time(Utils.getNowDate());
        identifying.setPhone(et_phone.getText().toString());
        Log.e("xuzhiqi", "initData: " + identifying.getPhone());
        identifying.setPurpose("找回密码");

        //设置签名
        identifying.setSign2(Utils.md5(identifying.getRequest_start_time() + identifying.getPhone() + Utils.Signs));
        // 创建一个集合 来存储数据
        final HashMap<String, Object> map = new HashMap<>();
//        map.put("name", );
        map.put("user_id", identifying.getUser_id());
        map.put("version", identifying.getVersion());
        map.put("current_device", identifying.getCurrent_device());
        map.put("unique_identifier", "");
        map.put("user_defined_name", "");
        map.put("download_channel", "");
        map.put("phone_version", "");
        map.put("phone_model", "");
        map.put("wx_unionid", "");
        map.put("request_start_time", identifying.getRequest_start_time());
        map.put("phone", identifying.getPhone());
        map.put("purpose", identifying.getPurpose());
        map.put("sign2", identifying.getSign2());

        Log.e("xuzhiqi", "initData: " + identifying.getSign2());
        Observable<ResponseBody> data = myService.getData(map);
        data.subscribeOn(Schedulers.io());
        data.observeOn(AndroidSchedulers.mainThread());
        data.subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                try {
                    Log.e("liangxq", "onNext: " + value.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("xuzhiqi", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }

        });

    }


    //验证注册的密码 保存到后台
    private void intiPasswordData() {

        Toast.makeText(this, "验证账号密码", Toast.LENGTH_SHORT).show();

    }
}
