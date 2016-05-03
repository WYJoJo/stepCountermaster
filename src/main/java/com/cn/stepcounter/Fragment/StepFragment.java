package com.cn.stepcounter.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.stepcounter.R;
import com.cn.stepcounter.StepCounterService;
import com.cn.stepcounter.StepDetector;

import org.xutils.DbManager;

import java.text.DecimalFormat;
import java.util.List;

import constans.UserData;
import db.Db;
import db.User;
import utils.DataUtils;
import widget.CircleView;


public class StepFragment extends Fragment {

    private TextView tv_dis,tv_time,tv_cal,tv_pro,tv_target,tv_value,tv_type;
    private Thread thread;
    private CircleView circleView;

    private int total_step = 0;
    private Double distance = 0.0;
    private Double calories = 0.0;
    private int step_length = 50;

    private User user;
    private UserData userData;
    private MsgReceiever msgReceiever;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step,container,false);
        initView(view);
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    public void initView(View view){
        circleView = (CircleView)view.findViewById(R.id.step_cv);
        tv_cal = (TextView)view.findViewById(R.id.tv_cal);
        tv_dis = (TextView)view.findViewById(R.id.tv_dis);
        tv_pro = (TextView)view.findViewById(R.id.tv_pro);
        tv_target = (TextView)view.findViewById(R.id.tv_target);
        tv_time = (TextView)view.findViewById(R.id.tv_time);
        tv_value = (TextView)view.findViewById(R.id.tv_value);
        tv_type = (TextView)view.findViewById(R.id.tv_type);


    }

    private void init(){
        Intent service = new Intent(getActivity(),StepCounterService.class);
        getActivity().startService(service);
        userData = UserData.getInstance();
        msgReceiever = new MsgReceiever();
        IntentFilter intentFilter = new IntentFilter("step_receiver");
        getActivity().registerReceiver(msgReceiever, intentFilter);
    }

    private void initView(){
        tv_pro.setText(userData.getTotal_step() + "");
        tv_dis.setText(userData.getDistance() + "");
        tv_cal.setText(userData.getCalories() + "");
        tv_time.setText(DataUtils.getDateNow().substring(0, 11));
        if (userData.getType() == 0){
            tv_type.setText("跑步");
            circleView.setMaxValue(userData.getStep_run());
            tv_target.setText("今日目标：" + userData.getStep_run());
        }else if (userData.getType() == 2){
            tv_type.setText("爬楼梯");
            circleView.setMaxValue(userData.getStep_up());
            tv_target.setText("今日目标：" + userData.getStep_up());
        }else {
            tv_type.setText("跳绳");
            circleView.setMaxValue(userData.getStep_jump());
            tv_target.setText("今日目标：" + userData.getStep_jump());
        }
    }

    public void initView(int type){

        if (type == 0){
            tv_type.setText("跑步");
            circleView.setMaxValue(userData.getStep_run());
            tv_target.setText("今日目标：" + userData.getStep_run());
        }else if (type == 2){
            tv_type.setText("爬楼梯");
            circleView.setMaxValue(userData.getStep_up());
            tv_target.setText("今日目标：" + userData.getStep_up());
        }else {
            tv_type.setText("跳绳");
            circleView.setMaxValue(userData.getStep_jump());
            tv_target.setText("今日目标：" + userData.getStep_jump());
        }
    }



    /**
     * 计算并格式化doubles数值，保留两位有效数字
     *
     * @param doubles
     * @return 返回当前路程
     */
    private String formatDouble(Double doubles) {
        DecimalFormat format = new DecimalFormat("####.##");
        String distanceStr = format.format(doubles);
        return distanceStr;
    }

    public class MsgReceiever extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            total_step = intent.getIntExtra("total_step",0);
            tv_pro.setText(String.valueOf(total_step));
            circleView.setProgress(total_step);
            tv_dis.setText("" + intent.getFloatExtra("distance", 0));
            tv_cal.setText("" + intent.getFloatExtra("calories",0));
            float value = (float)(userData.getTotal_step() )/(float)(userData.getMax_value()) * 100;
            value = Math.round(value * 100) / 100f;
            tv_value.setText("已完成：" + value + "%");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Intent service = new Intent(getActivity(),StepCounterService.class);
//        getActivity().stopService(service);
        getActivity().unregisterReceiver(msgReceiever);
    }



}
