package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class HymnShow extends AppCompatActivity {
    ArrayList<String> lines=new ArrayList<>();
    ArrayList<GroupOfFour> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymn_show);

        getSupportActionBar().setTitle(getIntent().getStringExtra("sub"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      // String language="englsih";
        String language=getSharedPreferences("language",MODE_PRIVATE).getString("language","English");
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("data").child(language).child(getIntent().getStringExtra("sub"));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lines.clear();
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG).show();
                    lines.add(d.getKey());
                }
                int groups=(lines.size()/4)+(lines.size()%4==0?0:1);
                int count=0;
                for(int i=0;i<groups;i++)
                {
                    String x[]=new String[4]  ;
                    Arrays.fill(x,"NA");
                    GroupOfFour g=new GroupOfFour();
                    g.l1="";
                    g.l2="";
                    g.l3="";
                    g.l4="";
                    for(int k=0;k<4&&count<lines.size();k++)
                    {
                        x[k]=lines.get(count);
                        count++;
                    }
                    g.l1=x[0];
                    g.l2=x[1];
                    g.l3=x[2];
                    g.l4=x[3];
                    data.add(g);

                }
                ListView listView=(ListView)findViewById(R.id.hymn);
                listView.setAdapter(new HymnShowAdapter(getApplicationContext(),data,getIntent().getStringExtra("sub")));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hymnshowmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.downloadshow)
        {

           String english,hindi,tamil,telugu,meaning;
           final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("data").child("downloads").child(getIntent().getStringExtra("sub"));
           databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   DownloadType downloadType;
                   downloadType=dataSnapshot.getValue(DownloadType.class);
                   DBManager dbManager=new DBManager(getApplicationContext());
                   dbManager.open();
                   dbManager.insert(getIntent().getStringExtra("sub"),downloadType.english,downloadType.hindi,downloadType.tamil,downloadType.telugu,downloadType.meaning);
                   Toast.makeText(getApplicationContext(),"Downloading",Toast.LENGTH_LONG).show();
                   dbManager.close();

               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });

        }
        else if(item.getItemId()==R.id.trackedshow)
        {
           startActivity(new Intent(getApplicationContext(),TrackedActivity.class));
        }
        else if(item.getItemId()==R.id.meaningshow)
        {
            Intent i=new Intent(getApplicationContext(),MeaningsHymnActivity.class);
            i.putExtra("sub",getIntent().getStringExtra("sub"));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

