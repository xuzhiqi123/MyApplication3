package com.example.myapplication.util;

import com.example.myapplication.bean.CodeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MyService {
    String url = "http://test.yaofun.vip/api/verification_code/";
    @GET("send?")
    Observable<ResponseBody> getData(@QueryMap HashMap<String, Object> map);
}
