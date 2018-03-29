package com.example.tnv.jsouphtml.servive;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TNV on 3/27/2018.
 */

public class MyAsyncTaskImage extends AsyncTask<String,Integer,String> {

    private final String TAG = getClass().getName();
    private File folder;
    private static String fileName = null,pathFolder =  null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/MyApp");
        if (!folder.exists()){
            folder.mkdir();
            Log.e(TAG, "Tao folder thanh cong" );
        }else{
            Log.e(TAG, "Folder da ton tai" );
        }
    }

    private String getNameLink(String url){
        return url.substring(url.lastIndexOf("/")+1,url.length());
    }
    @Override
    protected String doInBackground(String... strings) {
        InputStream inputStream;
        OutputStream outputStream;
        int fileLength = 0,count = 0, total = 0;
        try {
            URL url = new URL(strings[0]);
            if (strings[0].isEmpty())
                return "Url error";
            fileName = getNameLink(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            fileLength = connection.getContentLength();
            Log.e(TAG, "FileLength  : "+fileLength );
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return "Error";
            inputStream = connection.getInputStream();
            outputStream = new FileOutputStream(folder+"/"+fileName);
            byte[] bytes = new byte[1024*2];
            while ((count = inputStream.read(bytes)) > 0){
                outputStream.write(bytes,0,bytes.length);
            }
            pathFolder = folder+"/"+fileName;
            return pathFolder;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
