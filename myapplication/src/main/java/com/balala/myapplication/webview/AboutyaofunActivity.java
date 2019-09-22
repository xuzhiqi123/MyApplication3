package com.balala.myapplication.webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;


public class AboutyaofunActivity extends AppCompatActivity {

    private ImageView mAboutBack;
    private Toolbar mToolbarAbout;
    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutyaofun);
        mAboutBack = findViewById(R.id.about_back);
        mToolbarAbout = findViewById(R.id.toolbar_about);
        mWeb = findViewById(R.id.web);
        initView();
    }

    private void initView() {
        //加载一个网页：
        mWeb.loadUrl("https://www.yaofun.vip/agreement.html");
        mWeb.setWebViewClient(new WebViewClient());

        mAboutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
