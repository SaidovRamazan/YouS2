package ru.ramazan.tubesleeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    //Intent intent;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        String youtubeUrl = "";

        if (Intent.ACTION_SEND.equals(action) && type != null)
            if ("text/plain".equals(type))
                youtubeUrl = handleSendText(intent); // Handle text being sent



        VideoLink.mainActivity = this;

        WebView webView  = findViewById(R.id.webView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);

        if (!Objects.equals(youtubeUrl, ""))
            new VideoLinkProvider(webView).setVideoLink(youtubeUrl);

    }


    String handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            return sharedText;
        }
        return "";
    }

}
