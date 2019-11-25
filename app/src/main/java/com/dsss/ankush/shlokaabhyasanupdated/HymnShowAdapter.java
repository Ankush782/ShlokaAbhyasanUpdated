package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class HymnShowAdapter extends BaseAdapter {
    ArrayList<GroupOfFour> s;
    int t;
    Context cc;
    String name;
    public HymnShowAdapter(Context c, ArrayList<GroupOfFour> s,String name)
    {
        this.s=s;
        this.cc=c;
        this.t=t;
        this.name=name;
    }
    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int i) {
        return s.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(cc).inflate(R.layout.group_four,null);

        }
        final TextView a,b,c,d;
        Button button;
        a=(TextView) view.findViewById(R.id.a);
        b=(TextView) view.findViewById(R.id.b);
        c=(TextView) view.findViewById(R.id.c);
        d=(TextView) view.findViewById(R.id.d);
       // button=(Button)view.findViewById(R.id.)
        a.setText(s.get(i).l1);
        b.setText(s.get(i).l2);
        c.setText(s.get(i).l3);
        d.setText(s.get(i).l4);
        ImageButton imageButton=(ImageButton)view.findViewById(R.id.addtolearn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name).child(s.get(i).l1).setValue("0");
                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name).child(s.get(i).l2).setValue("0");
                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name).child(s.get(i).l3).setValue("0");
                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name).child(s.get(i).l4).setValue("0");
                FirebaseDatabase.getInstance().getReference().child("maintainance").child("favourites").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name).setValue("x");
                Toast.makeText(cc,"Succesfully added to learning list and Favourites",Toast.LENGTH_LONG).show();



            }
        });

        return view;

    }
}
