package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TrackedShowActivityAdapter extends BaseAdapter {
    ArrayList<String> names;
    ArrayList<String> values;
    Context c;
    String hymnname;
    public TrackedShowActivityAdapter(Context c, ArrayList<String> names,ArrayList<String> values,String hymnname)
    {
        this.names=names;
        this.values=values;
        this.c=c;
        this.hymnname=hymnname;
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.trackedline,null);
        }
        TextView t=view.findViewById(R.id.trackedline);
        t.setText(names.get(i));
        int v=Integer.parseInt(values.get(i));
        switch (v)
        {
            case 1:
            {

                t.setBackgroundColor(c.getResources().getColor(R.color.read));
              break;
            }
            case 2:
            {
                t.setBackgroundColor(c.getResources().getColor(R.color.learnt));
               break;
            }
            default:
            {
                t.setBackgroundColor(c.getResources().getColor(R.color.notread));
            }
        }
        CardView cardView=view.findViewById(R.id.trackedcard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(c,view);
                popupMenu.getMenuInflater().inflate(R.menu.popmenu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.readline:
                            {
                                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(hymnname).child(names.get(i)).setValue("1");
                                break;
                            }
                            case R.id.notreadline:
                            {
                                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(hymnname).child(names.get(i)).setValue("0");

                                break;
                            }
                            case R.id.Learntline:
                            {
                                FirebaseDatabase.getInstance().getReference().child("maintainance").child("tracking").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(hymnname).child(names.get(i)).setValue("2");

                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }

        });

        return view;
    }
}
