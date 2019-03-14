package com.shailu.news.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.shailu.news.R;
import com.shailu.news.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;


public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected int layoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView.getSettings().setDomStorageEnabled(true);
        String fileName = "/data/data/" + getPackageName() + "/cache";
        webView.getSettings().setAppCachePath(fileName);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        String loadingUrl = getIntent().getStringExtra("LOADING_URL");

        progressBar.setVisibility(View.VISIBLE);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                if (progress == 100)
                    progressBar.setVisibility(View.GONE);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(loadingUrl);
    }

    @OnClick(R.id.btn_back)
    public void goBack(){
        super.onBackPressed();
    }

}
