package com.bwie.mydingdan.model;

import com.bwie.mydingdan.bean.MyDingDanBean;
import com.bwie.mydingdan.present.Presenter;
import com.bwie.mydingdan.url.OkHttp3Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/13.
 */

public class Model {
    private Presenter presenter;

    public Model(Presenter presenter) {

        this.presenter = presenter;
    }

    public void getDingUrl(String dingdan, int page) {
        Map<String, String> params=new HashMap<>();
        params.put("uid","3690");
        params.put("page",page+"");
        OkHttp3Util.doPost(dingdan, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String json = response.body().string();
                    MyDingDanBean myDingDanBean = new Gson().fromJson(json, MyDingDanBean.class);
                    presenter.getDingDanBean(myDingDanBean);


                }
            }
        });
    }
}
