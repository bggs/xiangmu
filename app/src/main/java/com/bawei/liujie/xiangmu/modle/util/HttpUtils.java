package com.bawei.liujie.xiangmu.modle.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.liujie.xiangmu.view.application.NetDataCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类的作用:
 * author: 刘婕
 * date:2017/7/20
 */

public class HttpUtils {

    private NetDataCallBack net;
    private Handler hanler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    net.callback(msg.obj);
                    break;
                case 1:
                    net.error((String) msg.obj,400);
                    break;
            }

        }
    };

    public <T>void LoadDataFromServer(String url, NetDataCallBack netDataCallBack, final Class<T> tClass) {
        this.net = netDataCallBack;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(200, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = e.getMessage().toString();
                hanler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = Message.obtain();
                Gson gson = new Gson();
                String string = response.body().string();
                Log.e("str", "onResponse: " + string);
                T t = gson.fromJson(string, tClass);
                msg.obj = t;
                msg.what=0;
                hanler.sendMessage(msg);
            }
        });
    }
}
