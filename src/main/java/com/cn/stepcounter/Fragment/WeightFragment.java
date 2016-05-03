package com.cn.stepcounter.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.stepcounter.ExitActivity;
import com.cn.stepcounter.R;

import constans.UserData;
import widget.ProgressView;


public class WeightFragment extends Fragment {

    private TextView tv_sex,tv_wei,tv_hei,tv_time,tv_run,tv_jump,tv_up,tv_cal,tv_edit;
    private ImageView iv_head;
    private ProgressView pro_s_d,pro_cal_d,pro_w_t,pro_cal_t;
    private UserData userData;

    public WeightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        tv_cal = (TextView)view.findViewById(R.id.cal_w);
        tv_hei = (TextView)view.findViewById(R.id.hei_w);
        tv_wei = (TextView)view.findViewById(R.id.wei_w);
        tv_jump = (TextView)view.findViewById(R.id.tv_jump);
        tv_run = (TextView)view.findViewById(R.id.tv_run);
        tv_sex = (TextView)view.findViewById(R.id.sex_w);
        tv_time = (TextView)view.findViewById(R.id.time_w);
        tv_up = (TextView)view.findViewById(R.id.tv_up);
        iv_head = (ImageView)view.findViewById(R.id.head_w);
        pro_cal_d = (ProgressView)view.findViewById(R.id.pro_fin_cal);
        pro_s_d = (ProgressView)view.findViewById(R.id.pro_fin_w);
        pro_cal_t = (ProgressView)view.findViewById(R.id.pro_t_c);
        pro_w_t = (ProgressView)view.findViewById(R.id.pro_t_w);
        tv_edit = (TextView)view.findViewById(R.id.tv_edit);
        userData = UserData.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
        show();
    }

    private void show(){
        tv_edit.setOnClickListener(onClickListener);
        tv_sex.setText("性别：" + userData.getSex());
        tv_hei.setText("目标体重：" + userData.getTarget_weight() + "kg");
        tv_wei.setText("当前体重：" + userData.getWeight() + "kg");
        tv_time.setText("所需时间：" + userData.getNeed_time() + "天");
        tv_run.setText(userData.getStep_run() + "");
        tv_up.setText(userData.getStep_up() + "");
        tv_jump.setText(userData.getStep_jump() + "");
        tv_cal.setText(userData.getCal_d() + "");

        if (userData.getTarget_weight() != 0) {

            float pro_cal = userData.getCalories() / userData.getCal_d() * 800;
            pro_cal_d.setPro(pro_cal);

            float pro_s;
            if (userData.getType() == 0) {
                pro_s = userData.getTotal_step() * 1.0f / userData.getStep_run() * 800;
                pro_s_d.setPro(pro_s);
            } else if (userData.getType() == 2) {
                pro_s = userData.getTotal_step() * 1.0f / userData.getStep_up() * 800;
                pro_s_d.setPro(pro_s);
            } else {
                pro_s = userData.getTotal_step() * 1.0f / userData.getStep_jump() * 800;
                pro_s_d.setPro(pro_s);
            }


            float pro_ct = userData.getCal_c() / userData.getCal_t() * 800;
            pro_cal_t.setPro(pro_ct);
            float pro_fin = userData.getTarget_weight() - userData.getWeight();
            float pro_wt = userData.getWeight_t() - pro_fin / userData.getWeight_t() * 800;
            pro_w_t.setPro(pro_wt);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), ExitActivity.class));
        }
    };
}
