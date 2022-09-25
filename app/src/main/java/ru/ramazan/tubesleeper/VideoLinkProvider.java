package ru.ramazan.tubesleeper;

import android.annotation.SuppressLint;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class VideoLinkProvider {
    String saveFromUrl = "http://ru.savefrom.net/#url=";
    static String videoLink = "";

    public String getVideoLink(String youtubeUrl, WebView webView) {

        webView.loadUrl(saveFromUrl + youtubeUrl);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                injectJavaScript(webView);
            }
        });
        webView.addJavascriptInterface(new JSBridge(), "Bridge");

        return videoLink;
    }

    public void injectJavaScript(WebView webView) {
        webView.loadUrl("javascript:(function(){setTimeout(() => { let url = document.querySelector('.download-icon').href; Bridge.calledFromJS(url) }, 15000) })()");
    }

    static class JSBridge {
        @JavascriptInterface
        public void calledFromJS(String url) {
            Log.d("АДРЕС С САЙТА: ", url);
            videoLink = url;
        }
    }

}
