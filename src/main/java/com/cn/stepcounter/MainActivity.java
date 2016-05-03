package com.cn.stepcounter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.stepcounter.Fragment.AnalysisFragment;
import com.cn.stepcounter.Fragment.SetFragment;
import com.cn.stepcounter.Fragment.StepFragment;
import com.cn.stepcounter.Fragment.WeightFragment;

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements SetFragment.OnHeadlineSelectedListener{

    private static final String STEP = "step";
    private static final String ANALYSIS = "analysis";
    private static final String WEIGHT = "weight";
    private static final String SET = "set";
    private TabHost mTabHost;
    private TabManager mTabManager;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
//        Intent intent = getIntent();
//        type = intent.getIntExtra("type",0);
        initView(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);// 必须要调用这句
    }

    private void initView(Bundle savedInstanceState){
        mTabHost = (TabHost)findViewById(R.id.tabHost);
        mTabHost.setup();
        mTabManager = new TabManager(this,mTabHost,R.id.realtabcontent);

       mTabManager.addTab(mTabHost.newTabSpec(STEP).setIndicator(getBottomView(0)), StepFragment.class,null);
       mTabManager.addTab(mTabHost.newTabSpec(ANALYSIS).setIndicator(getBottomView(1)), AnalysisFragment.class,null);
        mTabManager.addTab(mTabHost.newTabSpec(WEIGHT).setIndicator(getBottomView(2)),WeightFragment.class, null);
        mTabManager.addTab(mTabHost.newTabSpec(SET).setIndicator(getBottomView(3)), SetFragment.class, null);

//        if (type == 1){
//            mTabHost.setCurrentTab(2);
//        }
        mTabHost.setCurrentTab(0);

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }
    private View getBottomView(int i){
        View bottomView = LayoutInflater.from(this).inflate(R.layout.bottom_view,null);
        ImageView imageView = (ImageView) bottomView.findViewById(R.id.bottom_image);
        TextView textView = (TextView) bottomView.findViewById(R.id.bottom_txt);
        switch (i){
            case 0:
                imageView.setImageResource(R.drawable.step_bottom_selector);
                textView.setText("运动");
                break;
            case 1:
                imageView.setImageResource(R.drawable.analysis_bottom_selector);
                textView.setText("分析");
                break;
            case 2:
                imageView.setImageResource(R.drawable.weight_bottom_selector);
                textView.setText("减肥");
                break;
            case 3:
                imageView.setImageResource(R.drawable.set_bottom_selector);
                textView.setText("设置");
                break;
            default:
                break;
        }
        return bottomView;
    }

   public void aboutMe(View view){
       startActivity(new Intent(this,MeActivity.class));
   }

    public static class TabManager implements TabHost.OnTabChangeListener {
        private final FragmentActivity mActivity;
        private final TabHost mTabHost;
        private final int mContainerId;
        private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        TabInfo mLastTab;

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _class, Bundle _args) {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory {
            private final Context mContext;

            public DummyTabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
            mActivity = activity;
            mTabHost = tabHost;
            mContainerId = containerId;
            mTabHost.setOnTabChangedListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new DummyTabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                ft.detach(info.fragment);
                ft.commit();
            }

            mTabs.put(tag, info);
            mTabHost.addTab(tabSpec);
        }

        @Override
        public void onTabChanged(String tabId) {
            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab) {
                FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                if (mLastTab != null) {
                    if (mLastTab.fragment != null) {
//	                     ft.detach(mLastTab.fragment);
                        ft.hide(mLastTab.fragment);
                    }
                }
                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity,
                                newTab.clss.getName(), newTab.args);
                        ft.add(mContainerId, newTab.fragment, newTab.tag);
                    } else {
//	                     ft.attach(newTab.fragment);
                        ft.show(newTab.fragment);
                    }
                }

                mLastTab = newTab;
                ft.commit();
                mActivity.getSupportFragmentManager().executePendingTransactions();
            }
        }
    }

    @Override
    public void chengeType(int type) {
        StepFragment stepFragment = (StepFragment)getSupportFragmentManager().findFragmentByTag("step");
        stepFragment.initView(type);
    }
}
