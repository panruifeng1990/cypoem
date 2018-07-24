package com.zhpan.cypoem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhpan.module_common.router.RouterURL;
import com.zhpan.module_find.fragment.FindFragment;
import com.zhpan.module_home.fragment.HomeFragment;
import com.zhpan.module_me.fragment.MeFragment;
import com.zhpan.module_publish.fragment.PublishFragment;

import java.util.List;

/**
 * 主页面Fragment切换数据源 <br>
 * 日期：2014年10月26日 <br>
 *
 * @author：davintan
 */
public class AdapterFragmentPager extends FragmentPagerAdapter {

    /** 首页 */
    public static final int PAGE_HOME = 0;

    /**
     * 个人中心
     */
    public static final int PAGE_ME = 4;

    /**
     * 消息界面
     */
    public static final int PAGE_MESSAGE = 3;

    /**
     * 发布
     */
    public static final int PAGE_PUBLISH = 2;

    /**
     * 界面
     */
    public static final int PAGE_FIND = 1;


    /**
     * 切换Fragment页面集合
     */
    private SparseArray<Fragment> fragmentList = new SparseArray<>();


    public AdapterFragmentPager(FragmentManager fm) {
        super(fm);
        FindFragment findFragment = (FindFragment)ARouter.getInstance().build(RouterURL.FRAGMENT_FIND).navigation();
        HomeFragment homeFragment = (HomeFragment)ARouter.getInstance().build(RouterURL.FRAGMENT_HOME).navigation();
        PublishFragment publishFragment = (PublishFragment)ARouter.getInstance().build(RouterURL.FRAGMENT_PUBLISH).navigation();
        MeFragment meFragment = (MeFragment)ARouter.getInstance().build(RouterURL.FRAGMENT_ME).navigation();
        fragmentList.put(PAGE_HOME,homeFragment );
        fragmentList.put(PAGE_FIND,findFragment );
        fragmentList.put(PAGE_PUBLISH, publishFragment);
        fragmentList.put(PAGE_MESSAGE, meFragment);
    }

    public AdapterFragmentPager(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentList.put(position, fragment);
        return fragment;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        } else {
            return 0;
        }
    }

}
