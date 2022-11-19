package ru.ramazan.tubesleeper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class VideoLinkProvider {

    VideoLinkProvider(){}

    VideoLinkProvider(WebView webView) {
        this.webView = webView;
    }

    WebView webView;

    String saveFromUrl = "http://ru.savefrom.net/#url=";

    public void setVideoLink(String youtubeUrl) {

        webView.loadUrl(saveFromUrl + youtubeUrl);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                injectJavaScript();
            }
        });
        webView.addJavascriptInterface(new JSBridge(), "Bridge");

    }

    public void injectJavaScript() {
        webView.loadUrl("javascript:(function(){setTimeout(() => { let url = document.querySelector('.download-icon').href; Bridge.calledFromJS(url) }, 10000) })()");
    }

    static class JSBridge {
        @SuppressLint("SetJavaScriptEnabled")
        @JavascriptInterface
        public void calledFromJS(String url) {
            Log.d("АДРЕС С САЙТА: ", url);
            VideoLink.videoLink = url;

            Intent myIntent = new Intent(VideoLink.mainActivity, VideoActivity.class);
            VideoLink.mainActivity.startActivity(myIntent);
        }
    }

}
