package com.shailu.news.util;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private static final String PREF_COUNTRY_CODE_NAME = "countryCodeName";

    private SharedPreferences sharedPreferences;

    public MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
    }

    public String getPrefCountryCodeName() {
        return sharedPreferences.getString(PREF_COUNTRY_CODE_NAME, "IN");
    }

    public void setCountryCodeName(String countryCodeName) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PREF_COUNTRY_CODE_NAME, countryCodeName);
        edit.apply();
    }
}
