package com.dsss.ankush.shlokaabhyasanupdated;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DownloadShowActivity extends AppCompatActivity {
    String data,meaning;
    String name;
    ArrayList<String> d=new ArrayList<>();
    ArrayList<String> m=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_show);
        getSupportActionBar().setTitle("Hymn");
        data=getIntent().getStringExtra("data");
        meaning=getIntent().getStringExtra("meaning");
        ListView listView=(ListView)findViewById(R.id.doenloadlistshow);
        String arr[]=data.split(" ");
        for(String s:arr)
        {
            d.add(s);
        }
        arr=meaning.split(" ");
        for(String s:arr)
        {
            m.add(s);
        }
        listView.setAdapter(new MeaningAdapter(getApplication(),d,m));

    }
}
