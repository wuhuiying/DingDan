package com.bwie.mydingdan.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bwie.mydingdan.R;
import com.bwie.mydingdan.adapter.MyDingDanAdapter;
import com.bwie.mydingdan.bean.MyDingDanBean;
import com.bwie.mydingdan.present.Presenter;
import com.bwie.mydingdan.url.ApiUtil;
import com.bwie.mydingdan.view.Main;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/13.
 */

public class Frag_Daizhi extends Fragment implements Main{

    private List<MyDingDanBean.DataBean> list = new ArrayList<>();
    private View view;
    private RecyclerView recycler_daizhi;
    private int status=0;
    private Presenter presenter;
    private RelativeLayout relative_bar;
//    private SmartRefreshLayout smart_refresh;
    private int page=0;
    private int a=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                page++;
                a=1;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_daizhi, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler_daizhi = view.findViewById(R.id.recycler_daizhi);
        relative_bar = view.findViewById(R.id.relative_bar);
//        smart_refresh = view.findViewById(R.id.smart_refresh);
        presenter = new Presenter(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        relative_bar.setVisibility(View.VISIBLE);
        if (a==1){
            //page++;
           // presenter.getDingUrl(ApiUtil.dingdan,page);
        }
        presenter.getDingUrl(ApiUtil.dingdan,page);
    }

    @Override
    public void getDingDanBean(final MyDingDanBean myDingDanBean) {
      getActivity().runOnUiThread(new Runnable() {
            @Override
           public void run() {
               if (myDingDanBean!=null){
                    list.addAll(myDingDanBean.getData());
                    myAdapter();
                   relative_bar.setVisibility(View.GONE);
//                    smart_refresh.setOnRefreshListener(new OnRefreshListener() {
//                        @Override
//                        public void onRefresh(RefreshLayout refreshlayout) {
//                            list.clear();
//                            list.addAll(0,myDingDanBean.getData());
//                            myAdapter();
//                            smart_refresh.finishRefresh();
//                        }
//                    });
//                    smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
//                        @Override
//                        public void onLoadmore(RefreshLayout refreshlayout) {
//                            handler.sendEmptyMessage(0);
//                            list.clear();
//                            page++;
//                            presenter.getDingUrl(ApiUtil.dingdan,page);
//                            onResume();
//                            list.addAll(myDingDanBean.getData());
//                            myAdapter();
//                            smart_refresh.finishLoadmore();
//                        }
//                    });
//
              }
           }
        });
    }
    public void myAdapter(){
        recycler_daizhi.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        MyDingDanAdapter adapter = new MyDingDanAdapter(getActivity(), list,presenter,page);
        recycler_daizhi.setAdapter(adapter);
    }
}
