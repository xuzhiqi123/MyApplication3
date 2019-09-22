package com.balala.myapplication.util;

import android.widget.Toast;


/**
 * Created by $lzj on 2019/3/25.
 */
public class ToastUtils {

    public static void showShort(String msg){
        Toast.makeText(MyApp.getMyApp(),msg.toString(),Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg){
        Toast.makeText(MyApp.getMyApp(),msg.toString(),Toast.LENGTH_LONG).show();
    }
}
