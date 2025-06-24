package com.example.foodies5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class ShowWebActivity extends AppCompatActivity {
WebView webView;
    private static final String TAG = "ShowWebActivity";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web);
        webView=findViewById(R.id.show_web);
        Bundle bundle=getIntent().getExtras();
        assert bundle != null;
        String url=(String)bundle.get("Web Url");
        Log.d(TAG,"URL"+url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(webView.canGoBack())
            webView.goBack();
        else
            Toast.makeText(this, "No Page to go back!!!!", Toast.LENGTH_LONG).show();
    }

}