package yonky.yiqi.v.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import yonky.yiqi.base.BaseFragment;

public class ViewPagerAdaper extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    public ViewPagerAdaper(FragmentManager fm ,List<BaseFragment>fragments) {
        super(fm);
        this.fragments = fragments;
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
