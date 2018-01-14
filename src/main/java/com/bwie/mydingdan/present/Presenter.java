package com.bwie.mydingdan.present;

import com.bwie.mydingdan.bean.MyDingDanBean;
import com.bwie.mydingdan.fragment.Frag_Daizhi;
import com.bwie.mydingdan.model.Model;
import com.bwie.mydingdan.view.Main;

/**
 * Created by admin on 2018/1/13.
 */

public class Presenter implements PresenterPort {


    private Main main;
    private final Model model;

    public Presenter(Main main) {
        model = new Model(this);
        this.main = main;
    }

    @Override
    public void getDingDanBean(MyDingDanBean myDingDanBean) {
        main.getDingDanBean(myDingDanBean);
    }

    public void getDingUrl(String dingdan, int page) {
        model.getDingUrl(dingdan,page);
    }
}
