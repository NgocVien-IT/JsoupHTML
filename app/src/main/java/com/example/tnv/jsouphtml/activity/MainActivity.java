package com.example.tnv.jsouphtml.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tnv.jsouphtml.adapter.RecyclerViewAdapter;
import com.example.tnv.jsouphtml.controller.DBHelper;
import com.example.tnv.jsouphtml.model.EnglishDetails;
import com.example.tnv.jsouphtml.servive.BackgroundTaskReadSQLite;
import com.example.tnv.jsouphtml.servive.MyAsyncTack;
import com.example.tnv.jsouphtml.R;
import com.example.tnv.jsouphtml.adapter.EnglishAdapter;
import com.example.tnv.jsouphtml.model.English;
import com.example.tnv.jsouphtml.servive.MyAsyncTaksDetails;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnClickItemRecyclerview {
    private static final String TAG = "abc";
    Toolbar toolbar;
    GridView gridViewEnglish;
    ArrayList<English> list;
    List<English> listLocal;
    List<EnglishDetails> listDetails;
    public static final String URL_LINK = "link";
    MaterialSearchView searchView;
    DBHelper dbHelper;
    EnglishAdapter englishAdapter;
    MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.searchviewnaim);
        list = new ArrayList<>();
        listLocal = new ArrayList<>();
        dbHelper = new DBHelper(this);
        MyAsyncTack myAsyncTack = new MyAsyncTack(this);
        BackgroundTaskReadSQLite taskReadSQLite = new BackgroundTaskReadSQLite(this);

        if (checkNetworkConnection(this)) {
            try {
                list = (ArrayList<English>) myAsyncTack.execute("http://600tuvungtoeic.com/").get();
                englishAdapter = new EnglishAdapter(this, R.layout.row_item_english, list);
                Log.e(TAG, "ListConn Size " + list.size());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No Internet Access", Toast.LENGTH_SHORT).show();
            try {
                listLocal = (ArrayList<English>) taskReadSQLite.execute("Tran Ngoc Vien").get();

                if (listLocal.size() == 0) {
                    Log.e(TAG, "Loi listLocal : " + listLocal.size());
                } else {
                    englishAdapter = new EnglishAdapter(this, R.layout.row_item_english, listLocal);
                    Log.e(TAG, "ListLocal Size: " + listLocal.size());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


        gridViewEnglish = findViewById(R.id.grv_english);
        gridViewEnglish.setAdapter(englishAdapter);


        gridViewEnglish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetalisEnglish.class);
                intent.putExtra(URL_LINK, list.get(i).getmUrlNextPage());
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });



        int ii=0;
        for (English english : list) {
    ii++;
            try {
                MyAsyncTaksDetails taksDetails = new MyAsyncTaksDetails();
                listDetails = taksDetails.execute(english.getmUrlNextPage()).get();
                Log.e(TAG, "onCreateMain: "+listDetails.size() );
                Thread.sleep(1000);
                for (int i = 0; i < listDetails.size(); i++) {
                    //Log.e(TAG, "So lan  "+i );
                    String word = listDetails.get(i).getmWord();
                    String image = listDetails.get(i).getmImage();
                    String category = listDetails.get(i).getmCategory();
                    String easyRead = listDetails.get(i).getmEasyRead();
                    String example = listDetails.get(i).getmExample();
                    String translate = listDetails.get(i).getmTranslate();
                    EnglishDetails details = new EnglishDetails(image, word, easyRead, category, example, translate);
                    dbHelper.insertIntoDataDetails(details);
                }
                Log.e(TAG, "Ngu 1 milisecond" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "so luong: "+ii );
        }

//        MyThread myThread = new MyThread();
//        myThread.start();
    }


    public class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String link = bundle.getString("link");
            int i = bundle.getInt("pos");
            try {
                MyAsyncTaksDetails taksDetails = new MyAsyncTaksDetails();
                listDetails = taksDetails.execute(link).get();
                String word = listDetails.get(i).getmWord();
                String image = listDetails.get(i).getmImage();
                String category = listDetails.get(i).getmCategory();
                String easyRead = listDetails.get(i).getmEasyRead();
                String example = listDetails.get(i).getmExample();
                String translate = listDetails.get(i).getmTranslate();
                EnglishDetails details = new EnglishDetails(image, word, easyRead, category, example, translate);
                dbHelper.insertIntoDataDetails(details);
                Log.e(TAG, "handleMessage: Thanh Cong " );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyThread extends Thread{
        @Override
        public void run() {
            Message message = myHandler.obtainMessage();
            Bundle bundle = new Bundle();
            for (int i = 0; i<list.size();i++){
                String meg = list.get(i).getmUrlNextPage();
                bundle.putString("link",meg);
                bundle.putInt("pos",i);
                message.setData(bundle);
                myHandler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu); //your file name
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    private Boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void playService(int i) {
        Toast.makeText(this, "Name : " + list.get(i).getmName(), Toast.LENGTH_SHORT).show();
    }
}
