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

public class FavouritesActivity extends AppCompatActivity {
    ArrayList<String> names=new ArrayList<>();
    ArrayList<HymnType> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getSupportActionBar().setTitle("Favourites");
        final ListView listView=(ListView)findViewById(R.id.favlist) ;
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("maintainance").child("favourites").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    names.add(d.getKey());
                    data.add(new HymnType());

                }

                listView.setAdapter(new HymnsAdapter(getApplicationContext(),names,data));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
