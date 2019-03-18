package com.shailu.jankari.ui.list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] newsCategory = {"General", "Sports", "Business", "Entertainment",  "Health", "Science", "Technology"};


    @Override
    public Fragment getItem(int i) {
        return ListFragment.newInstance(newsCategory[i]);
    }

    @Override
    public int getCount() {
        return newsCategory.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsCategory[position];
    }
}