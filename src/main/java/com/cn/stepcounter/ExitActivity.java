package com.cn.stepcounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import constans.UserData;

public class ExitActivity extends Activity {

    private EditText edt_sex,edt_w,edt_h,edt_t,edt_time;
    private ImageView iv_back;
    private Button btn;
    private UserData userData;
    private float cal,total_cal;
    private float target_w,weight,height;
    private int time;
    private String sex;
    private float area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        initView();
        userData = UserData.getInstance();
        show();
    }

    private void initView(){
        edt_h = (EditText)findViewById(R.id.edt_h);
        edt_sex = (EditText)findViewById(R.id.edt_sex);
        edt_t = (EditText)findViewById(R.id.edt_t);
        edt_time = (EditText)findViewById(R.id.edt_time);
        edt_w = (EditText)findViewById(R.id.edt_w);
        btn = (Button)findViewById(R.id.btn_save);
        iv_back = (ImageView)findViewById(R.id.back);
        btn.setOnClickListener(onClickListener);
        iv_back.setOnClickListener(onClickListener);

    }

    private void show(){
        edt_h.setText(userData.getHeight() + "");
        edt_w.setText(userData.getWeight() + "");
        edt_time.setText(userData.getNeed_time() + "");
        edt_t.setText(userData.getTarget_weight() + "");
        edt_sex.setText(userData.getSex() + "");
    }

    private void init(){
        height = Float.parseFloat(edt_h.getText().toString());
        weight = Float.parseFloat(edt_w.getText().toString());
        target_w = Float.parseFloat(edt_t.getText().toString());
        time = Integer.parseInt(edt_time.getText().toString());
        sex = edt_sex.getText().toString();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.btn_save:
                   init();
                   countCal();
                   userData.setHeight(height);
                   userData.setWeight(weight);
                   userData.setTarget_weight(target_w);
                   userData.setNeed_time(time);
                   userData.setSex(sex);
                   userData.setCal_t(total_cal);
                   userData.setCal_d(cal);
                   userData.setWeight_t(target_w - weight);
                   userData.setStep_run(countStepRun());
                   userData.setStep_up(countStepUp());
                   userData.setStep_jump(countStepJump());
                   Toast.makeText(getApplicationContext(),"信息保存成功~",Toast.LENGTH_SHORT).show();
                   finish();
                   break;
               case R.id.back:
//                   startActivity(new Intent(getApplicationContext(),MainActivity.class));
                   finish();
                   break;
           }

        }
    };

    private int countStepRun(){
        int step_run = 0;
        getArea();
        step_run = (int)((cal + 1993.57 - 2213.09 * area) / 0.05);
//        step_run = (int)(cal / 0.029);
        return step_run;
    }

    private int countStepUp(){
        int step_up = 0;
        getArea();
        step_up = (int)((cal + 1993.57 - 2213.09 * area) / (0.05 * 0.176));
//        step_up = (int)(cal / 0.16);
        return step_up;
    }

    private int countStepJump(){
        int step_jump = 0;
        step_jump = (int)((cal + 1993.57 - 2213.09 * area) / (0.05 * 0.178));
//        step_jump = (int)(cal / 0.18);
        return step_jump;
    }

    private float countCal(){
        cal = 0;
        countTotalCal();
        cal = total_cal / time;
        return cal;
    }

    private float countTotalCal(){
        total_cal = 0;
        total_cal = (weight - target_w) * 7720;
        return total_cal;
    }

    private void getArea(){
        area = 0.0061f * userData.getHeight() + 0.0128f * userData.getWeight() - 0.1529f;
        area = area * 0.0001f;
    }

}
