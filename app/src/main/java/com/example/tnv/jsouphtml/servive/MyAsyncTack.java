package com.example.tnv.jsouphtml.servive;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tnv.jsouphtml.controller.DBHelper;
import com.example.tnv.jsouphtml.model.English;
import com.example.tnv.jsouphtml.model.EnglishDetails;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
        String result = null, topic = null, image = null, nextPage = null;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            result = response.body().string();

            //Chuyen thanh trang html
            Document document = Jsoup.parse(result);
            DBHelper dbHelper = new DBHelper(mContext);

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
                        nextPage = elementNextPage.attr("href");
                    }
                    English english = new English(topic, image,"http://600tuvungtoeic.com/"+ nextPage);

                    //dbHelper.insertIntoData(english);
                    list.add(english);
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

        }else{
            Log.e(TAG, "Result error : "+englishes.size());
        }
    }
}
