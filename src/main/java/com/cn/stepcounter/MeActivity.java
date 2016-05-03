package com.cn.stepcounter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import constans.UserData;
import utils.ColorUtils;

public class MeActivity extends Activity {

    private ImageView head,back;
    private TextView tv_w,tv_h,tv_b,tv_health;

    private PopupWindow popupWindow;
    private View view;
    private UserData userData;

    private float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        show();
    }

    private void initView(){
        head = (ImageView)findViewById(R.id.iv_head_me);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(onClickListener);
//        head.setOnClickListener(onClickListener);

        tv_b = (TextView)findViewById(R.id.tv_bmi);
        tv_h = (TextView)findViewById(R.id.tv_he_me);
        tv_health = (TextView)findViewById(R.id.tv_health);
        tv_w = (TextView)findViewById(R.id.tv_we_me);

        userData = UserData.getInstance();
    }

    private void show(){

        float height = userData.getHeight()/100f;
        bmi = userData.getWeight() / (height * height);

        tv_h.setText("当前身高  " + height + "m");
        tv_w.setText("当前体重  " + userData.getWeight() + "kg");
        tv_b.setText("BMI指数   "  + bmi);

        if (bmi < 18.5){
            tv_health.setText("健康状况  偏瘦");
        }else if (bmi > 18.5 && bmi < 24.99){
            tv_health.setText("健康状况  正常");
        }else if (bmi > 25 && bmi < 28){
            tv_health.setText("健康状况  偏重");
            tv_health.setTextColor(ColorUtils.FAT_COLOR);
        }else {
            tv_health.setText("健康状况  肥胖");
            tv_health.setTextColor(ColorUtils.OBESITY_COLOR);
        }
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.back:
                   finish();
                   break;
           }}

    };


}
