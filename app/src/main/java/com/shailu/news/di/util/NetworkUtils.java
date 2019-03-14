package com.shailu.news.di.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Pratik Butani
 */
public class NetworkUtils extends BroadcastReceiver {


    /**
     * Context Variable
     */
    Context context;

    /**
     * Check whether Internet connection is available or not...
     */
    boolean connected;

    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    public static boolean checkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI ||
                    activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        this.context = context;
        connected = checkConnection(context);
      
        if (connected) {
			//Log.d("Connection Available");
        } else {
			//LOGD("Connection Unavailable");
		}
    }
}