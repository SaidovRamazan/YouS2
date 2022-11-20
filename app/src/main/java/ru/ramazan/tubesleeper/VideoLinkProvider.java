package ru.ramazan.tubesleeper;

import android.webkit.WebView;
import android.webkit.WebViewClient;


public class VideoLinkProvider {

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
        webView.addJavascriptInterface(new VideoOpener(), "VideoOpener");

    }

    public void injectJavaScript() {
        webView.loadUrl("javascript:(function(){setTimeout(() => { let url = document.querySelector('.download-icon').href; VideoOpener.calledFromJS(url) }, 10000) })()");
    }

}
