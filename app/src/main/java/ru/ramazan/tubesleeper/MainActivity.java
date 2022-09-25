package ru.ramazan.tubesleeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
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
        String videoLink = "";

        if (Intent.ACTION_SEND.equals(action) && type != null)
            if ("text/plain".equals(type))
                youtubeUrl = handleSendText(intent); // Handle text being sent






        WebView webView  = findViewById(R.id.webView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        //WebChromeClient webChromeClient = new WebChromeClient();

        if (!Objects.equals(youtubeUrl, ""))
            videoLink = new VideoLinkProvider().getVideoLink(youtubeUrl, webView);

        if (!Objects.equals(videoLink, ""))
            // webView.loadUrl(videoLink) // videoLink Содержит ссылку на видео. То есть, если её открыть, то уже откроется чистое видео.
            webView.loadUrl("https://vk.com"); // Однако пока не получается попросить WebView открыть эту ссылку. Надо понять, как попрость webView открыть нашу ссылку.
        // Тут я ради эксперимента попробовал открыть вконтакте. Но как видел, не работает. Разберись короче, как перейти на другую ссылку через один экземпрял WebView.
        // Там в группах поспрашивай и в гугле поиши.
        // Всё давай, завтра утром жду рабочий вариант.
            //webView.evaluateJavascript("(function() {alert('сообщение');})();", s -> {
            ////    Log.d("LogName", s); // Prints: "this"
            //}); // Посмотри что делает эта функция. Возможно это иньекция пользовательского скрипта на страницу.





        /*

        WebViewClient webViewClient = new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };

         */


    }


    String handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            return sharedText;
        }
        return "";
    }

}
