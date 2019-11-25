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

public class SubCategoryActivity extends AppCompatActivity {
    ArrayList<SubCategoryType> data=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
       getSupportActionBar().setTitle(getIntent().getStringExtra("sub"));

        final ListView listView=(ListView)findViewById(R.id.subcatlist) ;
        databaseReference= FirebaseDatabase.getInstance().getReference().child("data").child("subcategory").child(getIntent().getStringExtra("sub"));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    names.add(d.getKey());
                    data.add(d.getValue(SubCategoryType.class));
                }
                listView.setAdapter(new SubCategoryAdapter(getApplicationContext(),names,data));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
