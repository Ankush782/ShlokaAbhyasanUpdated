package com.dsss.ankush.shlokaabhyasanupdated;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HymnsActivity extends AppCompatActivity {
    ArrayList<HymnType> data=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymns);
        getSupportActionBar().setTitle(getIntent().getStringExtra("sub"));
        final ListView listView=(ListView)findViewById(R.id.hymnslist) ;
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("data").child("hymns").child(getIntent().getStringExtra("sub"));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    names.add(d.getKey());
                    data.add(d.getValue(HymnType.class));
                }

                listView.setAdapter(new HymnsAdapter(getApplicationContext(),names,data));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
