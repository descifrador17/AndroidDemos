package com.descifrador.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;

public class ArticleReader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_reader);


        Intent intent = getIntent();
        int position = intent.getIntExtra("Position",0);
        WebView webView = findViewById(R.id.articleWebView);
        webView.loadUrl(MainActivity.newsURL.get(position));

        webView.setBackgroundColor(Color.BLACK);

    }
}
