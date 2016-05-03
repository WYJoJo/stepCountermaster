package com.cn.stepcounter;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/1/8.
 */
public class Myapplication extends Application{

    private static Myapplication myapplication;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        myapplication = this;
    }

    public static Myapplication getInstance(){
        return myapplication;
    }
}
