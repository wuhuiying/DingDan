package com.bwie.mydingdan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.mydingdan.R;
import com.bwie.mydingdan.bean.MyDingDanBean;
import com.bwie.mydingdan.holder.MyDingDanHolder;
import com.bwie.mydingdan.present.Presenter;
import com.bwie.mydingdan.url.ApiUtil;
import com.bwie.mydingdan.url.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/1/13.
 */

public class MyDingDanAdapter extends RecyclerView.Adapter<MyDingDanHolder> {
    private final Context context;
    private final List<MyDingDanBean.DataBean> list;
    private Presenter presenter;
    private int page;

    public MyDingDanAdapter(Context context, List<MyDingDanBean.DataBean> list, Presenter presenter, int page) {

        this.context = context;
        this.list = list;
        this.presenter = presenter;
        this.page = page;
    }
    @Override
    public MyDingDanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dingdan,parent, false);
        MyDingDanHolder holder = new MyDingDanHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyDingDanHolder holder, final int position) {

        holder.text_title.setText(list.get(position).getTitle());
        holder.text_price.setText("价格: "+list.get(position).getPrice());
        holder.text_tame.setText(list.get(position).getCreatetime());
        if (list.get(position).getStatus()==0){
            holder.text_daizhifu.setText("待支付");
            holder.text_daizhifu.setTextColor(Color.RED);
            holder.text_btn.setText("取消订单");
            holder.text_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定要取消订单吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> params=new HashMap<>();
                            params.put("uid","3690");
                            params.put("orderId", String.valueOf(list.get(position).getOrderid()));
                            params.put("status", String.valueOf(2));
                            OkHttp3Util.doPost(ApiUtil.genxin, params, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()){
                                        presenter.getDingUrl(ApiUtil.dingdan,page);
                                    }
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();


                }
            });

        }else if (list.get(position).getStatus()==1){
            holder.text_daizhifu.setText("已支付");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
        }else {
            holder.text_daizhifu.setText("已取消");
            holder.text_daizhifu.setTextColor(Color.BLACK);
            holder.text_btn.setText("查看订单");
            holder.text_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("确定循环利用吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> params = new HashMap<>();
                            params.put("uid", "3690");
                            params.put("orderId", String.valueOf(list.get(position).getOrderid()));
                            params.put("status", String.valueOf(0));
                            OkHttp3Util.doPost(ApiUtil.genxin, params, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        presenter.getDingUrl(ApiUtil.dingdan, page);
                                    }
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
