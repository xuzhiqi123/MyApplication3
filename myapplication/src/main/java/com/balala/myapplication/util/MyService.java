package com.balala.myapplication.util;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MyService {
    //    String url = "http://test.yaofun.vip/api/verification_code/";
    @GET("/verification_code/send?")
    Observable<ResponseBody> getData(@QueryMap HashMap<String, Object> map);

}
