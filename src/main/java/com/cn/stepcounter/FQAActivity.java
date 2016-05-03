package com.cn.stepcounter;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class FQAActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fqa);
    }

    public void back(View view){
        finish();
    }
}
