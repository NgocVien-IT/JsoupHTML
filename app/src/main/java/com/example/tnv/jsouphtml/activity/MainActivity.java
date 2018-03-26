package com.example.tnv.jsouphtml.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tnv.jsouphtml.servive.MyAsyncTack;
import com.example.tnv.jsouphtml.R;
import com.example.tnv.jsouphtml.adapter.EnglishAdapter;
import com.example.tnv.jsouphtml.model.English;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    GridView gridViewEnglish;
    ArrayList<English> list;
    public static final String URL_LINK = "link";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = new ArrayList<>();
        MyAsyncTack myAsyncTack = new MyAsyncTack(this);
        try {
            list = (ArrayList<English>) myAsyncTack.execute("http://600tuvungtoeic.com/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        EnglishAdapter englishAdapter = new EnglishAdapter(this,R.layout.row_item_english, list);
        gridViewEnglish = findViewById(R.id.grv_english);
        gridViewEnglish.setAdapter(englishAdapter);


        gridViewEnglish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Log.d("abc", "onItemClick() returned: " + list.get(i).getmName()+"\n"+list.get(i).getmUrlNextPage() );
                Intent intent = new Intent(MainActivity.this,DetalisEnglish.class);
                intent.putExtra(URL_LINK,list.get(i).getmUrlNextPage());
                startActivity(intent);
            }
        });

    }
}
