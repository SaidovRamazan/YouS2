package ru.ramazan.tubesleeper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class VideoOpener {
    @SuppressLint("SetJavaScriptEnabled")
    @JavascriptInterface
    public void calledFromJS(String url) {
        Log.d("АДРЕС С САЙТА: ", url);
        VideoLink.videoLink = url;

        /* Теперь, когда ссылка получена, перейдём в VideoActivity */
        Intent myIntent = new Intent(VideoLink.mainActivity, VideoActivity.class);
        VideoLink.mainActivity.startActivity(myIntent);
    }
}
