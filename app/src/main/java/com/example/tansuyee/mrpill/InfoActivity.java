package com.example.tansuyee.mrpill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        int topicHome = intent.getIntExtra("topicHome", 0);
        String url = "";

        switch (topicHome) {
            case 1:
                url += "https://www.healthline.com/health/common-cold-symptoms";
                break;
            case 2:
                url += "https://www.healthline.com/symptom/dry-eyes";
                break;
            case 3:
                url += "https://www.healthline.com/health/chronic-pain";
                break;
            case 4:
                url += "https://www.healthline.com/health/stomach";
                break;
            case 5:
                url += "https://www.healthline.com/health/skin-disorders";
                break;
            default:
                url += "https://www.healthline.com/";
        }

        WebView webview = findViewById(R.id.webView);

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(url);
    }
}
