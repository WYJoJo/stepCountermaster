package com.cn.stepcounter.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.stepcounter.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import utils.ColorUtils;

public class AnalysisFragment extends Fragment {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MyFragmentAdapter myFragmentAdapter;
    private TextView tv_setp,tv_weight,tv_cal,tv1,tv2,tv3;

    public AnalysisFragment() {
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
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        initView(view);
        init();
        initPoint(view);
        viewPager.setAdapter(myFragmentAdapter);
        return view;
    }

    private void initView(View view){
        viewPager = (ViewPager)view.findViewById(R.id.ans_viewpager);
        tv1 = (TextView)view.findViewById(R.id.tv1);
        tv2 = (TextView)view.findViewById(R.id.tv2);
        tv3 = (TextView)view.findViewById(R.id.tv3);
        tv_cal = (TextView)view.findViewById(R.id.tv_cal_ans);
        tv_setp = (TextView)view.findViewById(R.id.tv_step);
        tv_weight = (TextView)view.findViewById(R.id.tv_weight);
        tv_weight.setOnClickListener(onClickListener);
        tv_setp.setOnClickListener(onClickListener);
        tv_cal.setOnClickListener(onClickListener);
    }

    private void init(){
        fragments = new ArrayList<>();
        fragments.add(new AnsStepFragment());
        fragments.add(new AnsWeightFragment());
        fragments.add(new AnsCalFragment());
        myFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager());
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter{
        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public void initPoint(View view){
        final ImageView[] imageViews = new ImageView[fragments.size()];
        LinearLayout point = (LinearLayout)view.findViewById(R.id.point);
        setVpImage(fragments.size(),imageViews,point);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % fragments.size();

                tv_cal.setTextColor(ColorUtils.DEFAULT_COLOR);
                tv_setp.setTextColor(ColorUtils.DEFAULT_COLOR);
                tv_weight.setTextColor(ColorUtils.DEFAULT_COLOR);
                tv1.setBackgroundColor(Color.TRANSPARENT);
                tv2.setBackgroundColor(Color.TRANSPARENT);
                tv3.setBackgroundColor(Color.TRANSPARENT);
                switch (position){
                    case 0:
                        tv1.setBackgroundColor(ColorUtils.SELEC_COLOR);
                        tv_setp.setTextColor(ColorUtils.SELEC_COLOR);
                        break;
                    case 1:
                        tv2.setBackgroundColor(ColorUtils.SELEC_COLOR);
                        tv_weight.setTextColor(ColorUtils.SELEC_COLOR);
                        break;
                    case 2:
                        tv3.setBackgroundColor(ColorUtils.SELEC_COLOR);
                        tv_cal.setTextColor(ColorUtils.SELEC_COLOR);
                        break;
                    default:
                        break;
                }

                for (int i = 0;i < fragments.size();i ++){
                    if (i == position){
                        imageViews[i].setImageResource(R.drawable.goal_uncheck_state_checked);
                    }else {
                        imageViews[i].setImageResource(R.drawable.goal_uncheck_state_uncheck);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * viewpager对圆点的初始化*/

    private void  setVpImage(int imageSize,ImageView[] imageViews,ViewGroup viewGroup) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 8, 10, 8);
        for (int i = 0; i < imageSize ; i ++){
            imageViews[i] = new ImageView(getActivity());
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
            if(i == 0){
                imageViews[i].setImageResource(R.drawable.goal_uncheck_state_checked);
            }else {
                imageViews[i].setImageResource(R.drawable.goal_uncheck_state_uncheck);
            }
            viewGroup.addView(imageViews[i],layoutParams);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tv_cal.setTextColor(ColorUtils.DEFAULT_COLOR);
            tv_setp.setTextColor(ColorUtils.DEFAULT_COLOR);
            tv_weight.setTextColor(ColorUtils.DEFAULT_COLOR);
            tv1.setBackgroundColor(Color.TRANSPARENT);
            tv2.setBackgroundColor(Color.TRANSPARENT);
            tv3.setBackgroundColor(Color.TRANSPARENT);
            switch (v.getId()){
                case R.id.tv_cal_ans:
                    tv3.setBackgroundColor(ColorUtils.SELEC_COLOR);
                    tv_cal.setTextColor(ColorUtils.SELEC_COLOR);
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.tv_weight:
                    tv2.setBackgroundColor(ColorUtils.SELEC_COLOR);
                    tv_weight.setTextColor(ColorUtils.SELEC_COLOR);
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.tv_step:
                    tv1.setBackgroundColor(ColorUtils.SELEC_COLOR);
                    tv_setp.setTextColor(ColorUtils.SELEC_COLOR);
                    viewPager.setCurrentItem(0);
                    break;
                default:
                    break;
            }
        }
    };

}
