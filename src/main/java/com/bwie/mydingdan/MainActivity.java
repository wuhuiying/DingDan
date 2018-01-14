package com.bwie.mydingdan;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwie.mydingdan.fragment.Frag_Daizhi;
import com.bwie.mydingdan.fragment.Frag_YiQu;
import com.bwie.mydingdan.fragment.Frag_YiZhi;


import static android.view.Gravity.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_btn;
    private TextView text_daizhi;
    private TextView text_yizhi;
    private TextView text_yiqu;
    private FrameLayout frame_layout;
    private View item_popup;
    private View view;
    private PopupWindow popupWindow;
    private TextView btn_daizhi;
    private TextView btn_yizhi;
    private TextView btn_yiqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image_btn = findViewById(R.id.image_btn);
        text_daizhi = findViewById(R.id.text_daizhi);
        text_yizhi = findViewById(R.id.text_yizhi);
        text_yiqu = findViewById(R.id.text_yiqu);
        frame_layout = findViewById(R.id.frame_layout);
//https://www.zhaoapi.cn/product/updateOrder?uid=4427&status=1
        //https://www.zhaoapi.cn/product/updateOrder?uid=4427&orderId=
        //https://www.zhaoapi.cn/product/getOrders?uid=4427&status2
        text_daizhi.setOnClickListener(this);
        text_yizhi.setOnClickListener(this);
        text_yiqu.setOnClickListener(this);
        Frag_Daizhi frag_daizhi = new Frag_Daizhi();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,frag_daizhi).commit();

        item_popup = View.inflate(this, R.layout.item_popup, null);
        view = View.inflate(this, R.layout.activity_main, null);
        popupWindow = new PopupWindow(item_popup
                , ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);


        btn_daizhi = item_popup.findViewById(R.id.btn_daizhi);
        btn_yizhi = item_popup.findViewById(R.id.btn_yizhi);
        btn_yiqu = item_popup.findViewById(R.id.btn_yiqu);
        btn_daizhi.setOnClickListener(this);
        btn_yizhi.setOnClickListener(this);
        btn_yiqu.setOnClickListener(this);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
                popupWindow.showAsDropDown(image_btn);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_daizhi:
                Frag_Daizhi frag_daizhi = new Frag_Daizhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,frag_daizhi).commit();
                break;
            case R.id.text_yizhi:
                Frag_YiZhi frag_yiZhi = new Frag_YiZhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,frag_yiZhi).commit();
                break;
            case R.id.text_yiqu:
                Frag_YiQu frag_yiQu = new Frag_YiQu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,frag_yiQu).commit();
                break;


            case R.id.btn_daizhi:
                Frag_Daizhi frag_daizhi2 = new Frag_Daizhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,frag_daizhi2).commit();
                popupWindow.dismiss();
                break;
            case R.id.btn_yizhi:
                Frag_YiZhi frag_yiZhi2 = new Frag_YiZhi();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,frag_yiZhi2).commit();
                popupWindow.dismiss();
                break;
            case R.id.btn_yiqu:
                Frag_YiQu frag_yiQu2 = new Frag_YiQu();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,frag_yiQu2).commit();
                popupWindow.dismiss();
                break;
        }
    }
}
