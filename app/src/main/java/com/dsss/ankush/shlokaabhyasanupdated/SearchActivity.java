package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ArrayList<String> names=new ArrayList<>();
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        names=getIntent().getStringArrayListExtra("data");
        SearchView searchView=(SearchView)findViewById(R.id.search);
        t=(TextView)findViewById(R.id.sug);


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if(names.contains(s))
        {
            Intent i=new Intent(getApplicationContext(),HymnShow.class);
            i.putExtra("sub",s);
            startActivity(i);
        }
        else
        {
            t.setText("No Such Hymn Yet");
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
