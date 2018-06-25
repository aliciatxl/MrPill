package com.example.tansuyee.mrpill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private WebView webView;
    private TextView webTitle;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        int topicHome = intent.getIntExtra("topicHome", 0);
        String url = "";
        String title ="";

        switch (topicHome) {
            case 1:
                url += "https://www.healthline.com/health/common-cold-symptoms";
                title += "Cough and Cold";
                break;
            case 2:
                url += "https://www.healthline.com/symptom/dry-eyes";
                title += "Dry Eyes";
                break;
            case 3:
                url += "https://www.healthline.com/health/chronic-pain";
                title += "Chronic Pain";
                break;
            case 4:
                url += "https://www.healthline.com/health/stomach";
                title += "Stomach Conditions";
                break;
            case 5:
                url += "https://www.healthline.com/health/skin-disorders";
                title += "Skin Conditions";
                break;
            default:
                url += "https://www.healthline.com/";
        }

        webView = findViewById(R.id.webView);
        webTitle = findViewById(R.id.web_title);
        imageButton = findViewById(R.id.imageButton);

        webTitle.setText(title);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
