package com.shailu.news.ui.main;

import android.os.Bundle;
import android.util.Log;

import com.hbb20.CountryCodePicker;
import com.shailu.news.R;
import com.shailu.news.base.BaseActivity;
import com.shailu.news.ui.list.ListFragment;
import com.shailu.news.ui.list.TopHeadlinesViewModel;
import com.shailu.news.util.MySharedPreference;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity{

    @BindView(R.id.ccp)
    CountryCodePicker countryCodePicker;

    @Inject
    MySharedPreference mySharedPreference;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment(), "News").commit();

        countryCodePicker.setOnCountryChangeListener(() -> {
            String countryNameCode = countryCodePicker.getSelectedCountryNameCode();
            mySharedPreference.setCountryCodeName(countryNameCode);
            ListFragment fragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("News");
            if(fragment!= null)
            fragment.updateData();
        });
    }

}
