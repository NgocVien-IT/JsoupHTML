package com.example.tnv.jsouphtml.servive;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tnv.jsouphtml.model.EnglishDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TNV on 3/28/2018.
 */

public class MyAsyncTaksDetails extends AsyncTask<String, Integer, List<EnglishDetails>> {

    private static final String TAG = "abc";
    private List<EnglishDetails> list = new ArrayList<>();

    @Override
    protected List<EnglishDetails> doInBackground(String... strings) {
        String html = null, topic = null, image = null, nextpage = null;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            html = response.body().string();

            int pos = 0;
            String match = "hinhanh";

            String vocabulary = null;

            while (html.indexOf(match, pos) > 0) {
                pos = html.indexOf(match, pos) + match.length();

                //image ;
                pos = html.indexOf("src=\"", pos) + 5;
                String imageUrl = html.substring(pos, html.indexOf("\"", pos));

                //tu vung
                pos = html.indexOf("important;\"", pos) + 12;
                vocabulary = html.substring(pos, html.indexOf("</", pos));
                //dich cho de doc;
                pos = html.indexOf("red;\">/", pos) + 7;
                String easyread = html.substring(pos, html.indexOf("</span", pos));
                pos = html.indexOf("class=\"bold\">", pos) + 13;
                String giaithic = html.substring(pos, html.indexOf("</span>", pos));
                pos = html.indexOf("</span>", pos) + 7;
                String noidunggiaithic = html.substring(pos, html.indexOf("<br>", pos));

                pos = html.indexOf("class=\"bold\">", pos) + 13;
                String tuloi = html.substring(pos, html.indexOf("</span>", pos));
                pos = html.indexOf("</span>", pos) + 7;
                String word = html.substring(pos, html.indexOf("<br>", pos));

                pos = html.indexOf("class=\"bold\">", pos) + 13;
                String vidu = html.substring(pos, html.indexOf("</span>", pos));

                pos = html.indexOf("</span>", pos) + 7;
                String example = html.substring(pos, html.indexOf("<br/>", pos));

                pos = html.indexOf("<b>", pos) + 3;
                String translate = html.substring(pos, html.indexOf("</b>", pos));
                pos = html.indexOf("source src=\"", pos) + 12;
                String audio = html.substring(pos, html.indexOf("\"", pos));
                //Log.e(TAG, "audio :  "+"http://600tuvungtoeic.com/"+audio );

                EnglishDetails details =
                        new EnglishDetails(imageUrl, vocabulary, easyread, word, example, translate);
                list.add(details);
            }
            Log.e(TAG, "MyAsyncTaskDetail : "+list.size() );
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}