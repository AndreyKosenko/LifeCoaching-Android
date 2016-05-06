package com.murphy.lifecoaching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class StoreActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        setTitle("Online Store");
        mWebView = (WebView) findViewById(R.id.main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url;
        int i = getIntent().getIntExtra("isBook",1);
        if (i==0)
            url = "https://www.etsy.com/listing/253794029/success-doesnt-sleep-apparel?ref=related-3";
        else
            url = "https://www.etsy.com/listing/246601099/success-doesnt-sleep?ref=shop_home_active_2";
        mWebView.loadUrl(url);

    }
}
