package com.example.tnv.jsouphtml.servive;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tnv.jsouphtml.model.English;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



/**
 * Created by TNV on 3/15/2018.
 */

public class MyAsyncTack extends AsyncTask<String, Integer, List<English>> {

    private Context mContext;
    private List<English> list = new ArrayList<>();

    static String TAG ="abc";
    public MyAsyncTack(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected List<English> doInBackground(String... strings) {
        String result = null, topic = null, image = null, nextpage = null;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            result = response.body().string();
            //Log.e(TAG, "doInBackground: "+result );
            int pos = 0;
            String match = "noidung";
            String vocabulary = null;
            while (result.indexOf(match,pos) > 0){
                pos = result.indexOf(match,pos)+match.length();
                //tu vung
                pos = result.indexOf(" important;",pos)+13;

                vocabulary = result.substring(pos,(result.indexOf("</span>") - pos));
                Log.e(TAG, "Vocabulary : "+vocabulary );
            }
            Log.e(TAG, "Vocabulary : "+vocabulary );
            //chuyen thanh trang html
            Document document = Jsoup.parse(result);



            if (document != null) {
                Elements elements = document.select("div.gallery-item");
                for (Element element : elements) {
                    Element elementTopic = element.getElementsByTag("h3").first();
                    Element elementImg = element.getElementsByTag("img").first();
                    Element elementNextPage = element.getElementsByTag("a").first();
                    if (elementImg != null) {
                        image = elementImg.attr("src");
                    }
                    if (elementTopic != null) {
                        topic = elementTopic.text();
                    }
                    if (elementNextPage != null) {
                        nextpage = elementNextPage.attr("href");
                    }
                    English english = new English(topic, image,"http://600tuvungtoeic.com/"+ nextpage);
                    list.add(english);
                    //Log.e(TAG, "Topic : "+english.getmName() +"\n"+english.getmUrlImage()+"\n"+english.getmUrlNextPage() );
                }
            }
            return  list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<English> englishes) {
        super.onPostExecute(englishes);
        if (englishes.size() > 0){
            Log.e(TAG, "Result : "+englishes.size());
        }else{
            Log.e(TAG, "Result error : "+englishes.size());
        }
    }
}
