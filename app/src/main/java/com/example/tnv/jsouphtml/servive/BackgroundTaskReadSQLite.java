package com.example.tnv.jsouphtml.servive;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tnv.jsouphtml.controller.DBHelper;
import com.example.tnv.jsouphtml.model.DBEnglish;
import com.example.tnv.jsouphtml.model.English;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TNV on 3/28/2018.
 */

public class BackgroundTaskReadSQLite extends AsyncTask<String,Integer,List<English>> {

    private Context mContext;
    private DBHelper db;
    private String TAG = "abc";
    List<English> list ;
    public BackgroundTaskReadSQLite(Context Context) {
        this.mContext = Context;

    }

    @Override
    protected List<English> doInBackground(String... strings) {
        Log.e(TAG, "doInBackground: "+ strings[0]);
        if (!strings[0].isEmpty()){
            Cursor cursor = db.getAllEnglish();
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(DBEnglish.ID));
                String name = cursor.getString(cursor.getColumnIndex(DBEnglish.NAME));
                String image = cursor.getString(cursor.getColumnIndex(DBEnglish.IMAGE));
                String url = cursor.getString(cursor.getColumnIndex(DBEnglish.URL));
                list.add(new English(id,name,image,url));
                //Log.e(TAG, "Id : " +list.get(cursor.getPosition()).getmId());
                Log.e(TAG, "Name " +list.get(cursor.getPosition()).getmName());
                //Log.e(TAG, "Image :" +list.get(cursor.getPosition()).getmUrlImage());
                //Log.e(TAG, "Url :" +list.get(cursor.getPosition()).getmUrlNextPage());
            }
            cursor.close();
            Log.e(TAG, "ListSize : "+list.size());
            return list;
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        db = new DBHelper(mContext);
        list = new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<English> list) {
        super.onPostExecute(list);
    }
}
