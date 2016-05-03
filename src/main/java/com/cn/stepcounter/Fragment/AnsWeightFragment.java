package com.cn.stepcounter.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.stepcounter.R;

import org.xutils.DbManager;
import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

import db.Db;
import db.User;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


public class AnsWeightFragment extends Fragment {

    private LineChartView lineChartView;
    private LineChartData lineChartData;
    private int numberOfLines = 1;
    private int numberOfPoints = 7;
    private int maxNumberOfLines = 4;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private TextView tv_new,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv_we;
    private LinearLayout ans_we;
    private DbManager db;
    private List<User> users;
    private int len;

    public AnsWeightFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ans_weight, container, false);
        init();
        initView(view);
        generateValues();
//        getData();
//        show();
        return view;
    }

    private void initView(View view){
        lineChartView = (LineChartView)view.findViewById(R.id.weight_line);
        tv1 = (TextView)view.findViewById(R.id.tv_w1);
        tv2 = (TextView)view.findViewById(R.id.tv_w2);
        tv3 = (TextView)view.findViewById(R.id.tv_w3);
        tv4 = (TextView)view.findViewById(R.id.tv_w4);
        tv5 = (TextView)view.findViewById(R.id.tv_w5);
        tv6 = (TextView)view.findViewById(R.id.tv_w6);
        tv7 = (TextView)view.findViewById(R.id.tv_w7);
        tv_new = (TextView)view.findViewById(R.id.tv_new_weight);
        tv_we = (TextView)view.findViewById(R.id.tv_ans_we);
        ans_we = (LinearLayout)view.findViewById(R.id.ans_we);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        show();
    }

    private void init(){
        try {
            db = Db.getInstance();
            users = db.selector(User.class).findAll();
            len = users.size();
        }catch (Exception e){

        }
    }

    private void getData(){
        List<Line> lines = new ArrayList<>();

        for (int i = 0;i < numberOfLines; ++i){
            List<PointValue> values = new ArrayList<>();
            for (int j = 0;j < numberOfPoints; ++j){
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }
            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[0]);
            line.setHasLines(true);
            line.setHasPoints(true);
            lines.add(line);

            lineChartData = new LineChartData(lines);

            Axis axisY = new Axis().setHasLines(true);
            lineChartData.setAxisYLeft(axisY);

            lineChartData.setBaseValue(Float.NEGATIVE_INFINITY);
            lineChartView.setLineChartData(lineChartData);
        }
    }

   /*private void generateValues() {
        List<User> users;
        try {
            db = Db.getInstance();
            users = db.selector(User.class).findAll();
            int len = users.size();
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = len - 7; j < len; ++j) {
                    randomNumbersTab[i][j] = users.get(j).getWeight();
                }
            }
        }catch (Exception e){

        }

    }*/

  /*  private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 0.1f;
            }
        }
    }*/

    private void generateValues() {
        try {
//            int len = users.size();
            if (len < 7){
                return;
            }else {
                tv_we.setVisibility(View.GONE);
                ans_we.setVisibility(View.VISIBLE);
                for (int i = 0; i < maxNumberOfLines; ++i) {
                    for (int j = 0; j < numberOfPoints; ++j) {
                        randomNumbersTab[i][j] = users.get(len - 7 + j).getWeight();
                    }
                }
            }

        }catch (Exception e){

        }
}


    private void show(){
//        int len = users.size();
        if (len < 7){

        }else {
            tv1.setText(users.get(len - 7).getDate().substring(5,10));
        tv2.setText(users.get(len - 6).getDate().substring(5,10));
        tv3.setText(users.get(len - 5).getDate().substring(5,10));
        tv4.setText(users.get(len - 4).getDate().substring(5,10));
        tv5.setText(users.get(len - 3).getDate().substring(5,10));
        tv6.setText(users.get(len - 2).getDate().substring(5,10));
        tv7.setText(users.get(len - 1).getDate().substring(5,10));

        }
        tv_new.setText("最新：" + users.get(len - 1).getWeight());
    }
}
