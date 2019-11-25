package com.dsss.ankush.shlokaabhyasanupdated;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DownloadsActivity extends AppCompatActivity {
    ArrayList<String> names=new ArrayList<>();
    ArrayList<String> data=new ArrayList<>();
    ArrayList<String> meanings=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);
        getSupportActionBar().setTitle("Downloaded Hymns");
        DBManager dbManager=new DBManager(this);
        dbManager.open();
        Cursor cursor=dbManager.fetch();
        String language=getSharedPreferences("language",MODE_PRIVATE).getString("language","english");
        int index=1;
        switch (language)
        {
            case "English":
            {
                index=1;
                break;
            }
            case "Hindi":
            {
                index=2;
                break;
            }
            case "Tamil":
            {
                index=3;
                break;
            }
            case "Telugu":
            {
                index=4;
                break;
            }

        }

        while(cursor.moveToNext())
        {
            names.add(cursor.getString(0));
            data.add(cursor.getString(index));
            meanings.add(cursor.getString(5));


        }
        ListView listView=(ListView)findViewById(R.id.downloadlist);
        listView.setAdapter(new DownloadActivityAdapter(getApplicationContext(),names,data,meanings));
    }
}
