package com.zhpan.module_main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhpan.library.base.mvc.activity.BaseVcActivity;
import com.zhpan.library.custom_view.MViewPaper;
import com.zhpan.library.utils.SharedPreferencesHelper;
import com.zhpan.module_main.adapter.AdapterFragmentPager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class MainActivity extends BaseVcActivity {
    @BindView(R2.id.rb_home)
    RadioButton mRbHome;
    @BindView(R2.id.rb_find)
    RadioButton mRbFind;
    @BindView(R2.id.rb_add)
    RadioButton mRbAdd;
    @BindView(R2.id.rb_message)
    RadioButton mRbMessage;
    @BindView(R2.id.rb_me)
    RadioButton mRbMe;
    @BindView(R2.id.rg_tab)
    RadioGroup rgTab;
    @BindView(R2.id.vp_fragment)
    MViewPaper mViewPager;
    //  退出时间间隔
    private long exitTime = 0;
    //  上一次RadioGroup选中的Id
    private int preCheckedId = R.id.rb_home;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferencesHelper.put(this, "isFirstIn", true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        initData();
        setListener();
//        reStartActivity();
    }

    private void initData() {
//        getToolbar().setVisibility(View.GONE);
        AdapterFragmentPager mAdapter = new AdapterFragmentPager(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    private void setListener() {
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_home) {
                    mViewPager.setCurrentItem(AdapterFragmentPager.PAGE_HOME, false);

                } else if (checkedId == R.id.rb_find) {
                    mViewPager.setCurrentItem(AdapterFragmentPager.PAGE_FIND, false);

                } else if (checkedId == R.id.rb_add) {
                    mViewPager.setCurrentItem(AdapterFragmentPager.PAGE_PUBLISH, false);

                } else if (checkedId == R.id.rb_message) {
                    if (messageClicked()) {
                        return;
                    }

                } else if (checkedId == R.id.rb_me) {
                    if (meClicked()) {
                        return;
                    }

                }
                preCheckedId = checkedId;
            }
        });

    }

    private boolean meClicked() {
        if (isLogin()) {
            mViewPager.setCurrentItem(AdapterFragmentPager.PAGE_ME, false);
            return false;
        } else {
            goToLogin();
            rgTab.check(preCheckedId);
            return true;
        }
    }

    private boolean messageClicked() {
        if (isLogin()) {
            mViewPager.setCurrentItem(AdapterFragmentPager.PAGE_MESSAGE, false);
            return false;
        } else {
            goToLogin();
            rgTab.check(preCheckedId);
            return true;
        }
    }

    private void goToLogin() {
//        LoginActivity.start(this);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isLogin() {
        return true;
//        return UserInfoTools.getIsLogin(this);
    }

}
