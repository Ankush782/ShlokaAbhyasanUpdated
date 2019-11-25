package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeaningsHymnActivity extends AppCompatActivity {
    ArrayList<String> line=new ArrayList<>();
    ArrayList<String> meanings=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meanings_hymn);

        final String hymnname=getIntent().getStringExtra("sub");
        getSupportActionBar().setTitle(hymnname);

        String language=getSharedPreferences("language",MODE_PRIVATE).getString("language","hymndata");
        final ListView listView=(ListView)findViewById(R.id.meaninglist);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("data").child(language).child(getIntent().getStringExtra("sub"));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    line.add(d.getKey());
                }
                DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference().child("data").child("meaning").child(hymnname);
                databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dd:dataSnapshot.getChildren())
                        {
                            meanings.add(dd.getKey());
                        }
                        listView.setAdapter(new MeaningAdapter(getApplicationContext(),line,meanings));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
