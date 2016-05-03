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

import com.cn.stepcounter.R;

import org.xutils.DbManager;

import java.util.ArrayList;
import java.util.List;

import db.Db;
import db.User;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;


public class AnsCalFragment extends Fragment {

    private ColumnChartView columnChartView;
    private ColumnChartData columnChartData;

    private boolean hasLabels = true;
    private boolean hasLabelForSelected = true;

    private TextView tv_ave,tv_total,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv_ca;
    private LinearLayout ans_ca;
    private float ave,total;

    public AnsCalFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ans_cal, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void initView(View view){
        columnChartView = (ColumnChartView) view.findViewById(R.id.cal_column);
        columnChartView.setOnValueTouchListener(new ValueTouchListener());
        tv1 = (TextView)view.findViewById(R.id.tv_c1);
        tv2 = (TextView)view.findViewById(R.id.tv_c2);
        tv3 = (TextView)view.findViewById(R.id.tv_c3);
        tv4 = (TextView)view.findViewById(R.id.tv_c4);
        tv5 = (TextView)view.findViewById(R.id.tv_c5);
        tv6 = (TextView)view.findViewById(R.id.tv_c6);
        tv7 = (TextView)view.findViewById(R.id.tv_c7);
        tv_ave = (TextView)view.findViewById(R.id.tv_ave_cal);
        tv_total = (TextView)view.findViewById(R.id.tv_total_cal);
        tv_ca = (TextView)view.findViewById(R.id.tv_ca);
        ans_ca = (LinearLayout)view.findViewById(R.id.ans_ca);
    }

    private void getData(){
        int numSubcolumns = 1;
        int numColumns = 7;
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        List<User> users;

        try {
            DbManager db = Db.getInstance();
            users = db.selector(User.class).findAll();
            int len = users.size();
            if (len < 7){

            }else {
                tv_ca.setVisibility(View.GONE);
                ans_ca.setVisibility(View.VISIBLE);
                for (int i = len - 7;i < len; ++i){
                    values = new ArrayList<>();
                    for (int j = 0; j < numSubcolumns; ++j) {
                        values.add(new SubcolumnValue(users.get(i).getCalories(),ChartUtils.COLORS[0]));
                    }
                    Column column = new Column(values);
                    column.setHasLabels(hasLabels);
                    column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                    columns.add(column);
                }

                tv1.setText(users.get(len - 7).getDate().substring(5,10));
                tv2.setText(users.get(len - 6).getDate().substring(5,10));
                tv3.setText(users.get(len - 5).getDate().substring(5,10));
                tv4.setText(users.get(len - 4).getDate().substring(5,10));
                tv5.setText(users.get(len - 3).getDate().substring(5,10));
                tv6.setText(users.get(len - 2).getDate().substring(5,10));
                tv7.setText(users.get(len - 1).getDate().substring(5,10));
            }

            total = 0;
            ave = 0;
            for (int i = 0;i < len;i ++){
                total += users.get(i).getCalories();
            }
            ave = total/users.size();
            ave = Math.round(ave * 100) * 0.01f;
            tv_total.setText("总计：" + total);
            tv_ave.setText("平均：" + ave);


        }catch (Exception e){
            e.printStackTrace();
        }

        columnChartData = new ColumnChartData(columns);
        Axis axisY = new Axis().setHasLines(true);

        columnChartData.setAxisYLeft(axisY);
        columnChartView.setColumnChartData(columnChartData);
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
//            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
