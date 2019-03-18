package com.shailu.jankari.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hbb20.CountryCodePicker;
import com.shailu.jankari.R;
import com.shailu.jankari.base.BaseActivity;
import com.shailu.jankari.ui.list.NewsPagerAdapter;
import com.shailu.jankari.util.MySharedPreference;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity{

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.ccp)
    CountryCodePicker countryCodePicker;

    @Inject
    MySharedPreference mySharedPreference;

    private NewsPagerAdapter newsPagerAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String countryCodeName = mySharedPreference.getPrefCountryCodeName();
        countryCodePicker.setCountryForNameCode(countryCodeName);
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(newsPagerAdapter);
        tabLayout.setupWithViewPager(pager);
        countryCodePicker.setOnCountryChangeListener(() -> {
            String countryNameCode = countryCodePicker.getSelectedCountryNameCode();
            mySharedPreference.setCountryCodeName(countryNameCode);
            newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager());
            pager.setAdapter(newsPagerAdapter);
        });
    }

}
