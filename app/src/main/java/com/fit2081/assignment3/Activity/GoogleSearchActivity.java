package com.fit2081.assignment3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.fit2081.assignment3.R;

public class GoogleSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_search);
        WebView webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String keywordSearch = intent.getStringExtra("keyword_search");
        // URL encode the search query to handle spaces and special characters
//        String encodedKeyword = URLEncoder.encode(keywordSearch, "UTF-8");
        // Load the search URL into the WebView
        webView.loadUrl("https://www.google.com/search?q=" + keywordSearch);
    }
}