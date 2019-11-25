package com.dsss.ankush.shlokaabhyasanupdated;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackedShowActivity extends AppCompatActivity {
    ArrayList<String> names=new ArrayList<>();
    ArrayList<String> values=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_show);
        getSupportActionBar().setTitle(getIntent().getStringExtra("sub"));
        final ListView listView=(ListView)findViewById(R.id.trackedshowlist);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(getIntent().getStringExtra("sub"));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    names.add(d.getKey());
                    values.add(d.getValue(String.class));
                }
                listView.setAdapter(new TrackedShowActivityAdapter(getApplicationContext(),names,values,getIntent().getStringExtra("sub")));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
