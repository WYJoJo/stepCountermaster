package com.cn.stepcounter.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cn.stepcounter.AboutActivity;
import com.cn.stepcounter.FQAActivity;
import com.cn.stepcounter.R;

import constans.UserData;
import io.techery.progresshint.addition.widget.SeekBar;
import utils.ColorUtils;

public class SetFragment extends Fragment {

    private SeekBar seekBar;
    private float sensitivity;
    private UserData userData;

    private RadioGroup radioGroup;
    private RadioButton rb_run,rb_jump,rb_up;
   private RelativeLayout about,problem;

    public SetFragment() {
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
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        seekBar = (SeekBar)view.findViewById(R.id.seekbar);
        userData = UserData.getInstance();
        radioGroup = (RadioGroup)view.findViewById(R.id.rg);
        rb_jump = (RadioButton)view.findViewById(R.id.rb_jump);
        rb_run = (RadioButton)view.findViewById(R.id.rb_run);
        rb_up = (RadioButton)view.findViewById(R.id.rb_up);
        about = (RelativeLayout)view.findViewById(R.id.about);
        problem = (RelativeLayout)view.findViewById(R.id.problem);
        about.setOnClickListener(onClickListener);
        problem.setOnClickListener(onClickListener);

        seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
                sensitivity = seekBar.getProgress() / 10;
                userData.setSensitivity(sensitivity);
                Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_LONG).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        switch (userData.getType()){
            case 0:
                rb_run.setChecked(true);
                break;
            case 1:
                rb_jump.setChecked(true);
                break;
            case 2:
                rb_up.setChecked(true);
                break;
            default:
                break;
        }


    }

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_run:
                    userData.setType(0);
                    mCallback.chengeType(0);
                    break;
                case R.id.rb_jump:
                    userData.setType(1);
                    mCallback.chengeType(1);
                    break;
                case R.id.rb_up:
                    userData.setType(2);
                    mCallback.chengeType(2);
                    break;
                default:
                    break;
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.about:
                    startActivity(new Intent(getActivity(), AboutActivity.class));
                    break;
                case R.id.problem:
                    startActivity(new Intent(getActivity(), FQAActivity.class));
                    break;
                default:
                    break;
            }
        }
    };

    OnHeadlineSelectedListener mCallback;

              // Container Activity must implement this interface
           public interface OnHeadlineSelectedListener {
               public void chengeType(int type);
           }

               @Override
      public void onAttach(Activity activity) {
               super.onAttach(activity);

              // This makes sure that the container activity has implemented
               // the callback interface. If not, it throws an exception
            try {
                      mCallback = (OnHeadlineSelectedListener) activity;
                  } catch (ClassCastException e) {
                        throw new ClassCastException(activity.toString()
                           + " must implement OnHeadlineSelectedListener");
                 }
           }

}
