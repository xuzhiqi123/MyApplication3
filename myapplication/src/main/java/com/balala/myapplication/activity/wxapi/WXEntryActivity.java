package com.balala.myapplication.activity.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.net.URLEncoder;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private BaseResp resp = null;
    //wx24009bcc9adc6318
    public static String WX_APP_ID = "wx24009bcc9adc6318";
    // 获取第一步的code后，请求以下链接获取access_token
//    private String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//    // 获取用户个人信息
//    private String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    //private String WX_APP_SECRET = "93755b7f4c401116411f61058cf1e6a7";
    //private String unionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        api = WXAPIFactory.createWXAPI(this, WX_APP_ID, false);
        api.handleIntent(getIntent(), this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        String result = "";
        if (resp != null) {
            resp = resp;
        }
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "授权成功";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
//             startActivity(new Intent());
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "取消授权";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "拒绝授权";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                result = "授权返回";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

    /**
     * 通过拼接的用户信息url获取用户信息
     */
//    private void getUserInfo(String user_info_url) {
//        Map<String, String> reqBody = new ConcurrentSkipListMap<>();
//        NetUtils netUtils = NetUtils.getInstance();
//        netUtils.postDataAsynToNet(user_info_url, reqBody,
//                new NetUtils.MyNetCall() {
//                    @Override
//                    public void success(Call call, Response response) throws IOException {
//                        String str = response.body().string();
//                        parseJSONUser(str);
//
//                    }
//
//                    @Override
//                    public void failed(Call call, IOException e) {
//
//                    }
//
//                });
//
//    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    /**
     * 获取access_token的URL（微信）
     *
     * @param code 授权时，微信回调给的
     * @return URL
     */
//    private String getCodeRequest(String code) {
//        String result = null;
//        GetCodeRequest = GetCodeRequest.replace("APPID",
//                urlEnodeUTF8(WX_APP_ID));
//        GetCodeRequest = GetCodeRequest.replace("SECRET",
//                urlEnodeUTF8(WX_APP_SECRET));
//        GetCodeRequest = GetCodeRequest.replace("CODE", urlEnodeUTF8(code));
//        result = GetCodeRequest;
//        return result;
//    }

//    /**
//     * 获取用户个人信息的URL（微信）
//     *
//     * @param access_token 获取access_token时给的
//     * @param openid       获取access_token时给的
//     * @return URL
//     */
//    private String getUserInfo(String access_token, String openid) {
//        String result = null;
//        GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
//                urlEnodeUTF8(access_token));
//        GetUserInfo = GetUserInfo.replace("OPENID", urlEnodeUTF8(openid));
//        result = GetUserInfo;
//        return result;
//    }
    private String urlEnodeUTF8(String str) {
        String result = str;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    // 解析返回的数据
//    private void parseJSONWithGSON(String jsonData) {
//        // 使用轻量级的Gson解析得到的json
//        Gson gson = new Gson();
//        WeixinBean appList = gson.fromJson(jsonData,
//                new TypeToken<WeixinBean>() {
//                }.getType());
//        // 控制台输出结果，便于查看
//        String str = getUserInfo(appList.getAccess_token(), appList.getOpenid());
//        Log.e("wz", "parseJSONWithGSON: str : " + str);
//        getUserInfo(str);
//    }
//
//    // 解析用户信息
//    private void parseJSONUser(String jsonData) {
//        // 使用轻量级的Gson解析得到的json
//        Gson gson = new Gson();
//        appList = gson.fromJson(jsonData,
//                new TypeToken<UnionIdBean>() {
//                }.getType());
//        // 控制台输出结果，便于查看
//        unionid = appList.getUnionid();
//        Log.e("wz", "parseJSONUser: unionid : " + unionid);
//        presenter = new LoginPresenter(this);
//        presenter.iswx(unionid);
//    }


}