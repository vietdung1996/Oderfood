package com.vietdung.oderfood.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vietdung.oderfood.ui.home.newfood.FragmentNewFood;
import com.vietdung.oderfood.ui.home.fragmentsaleof.FragmentHightLights;
import com.vietdung.oderfood.ui.home.fragmentsaleof.FragmentSaleOf;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(new FragmentSaleOf());
        mFragments.add(new FragmentNewFood());

        mFragmentTitles.add("Chương trình khuyến mãi");
        mFragmentTitles.add("Món mới");
      //  mFragmentTitles.add("Nổi bât");
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments==null ? 0: mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
