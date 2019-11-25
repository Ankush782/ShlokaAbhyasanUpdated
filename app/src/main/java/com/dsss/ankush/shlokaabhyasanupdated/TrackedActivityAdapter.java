package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackedActivityAdapter extends BaseAdapter {
    Context c;
    ArrayList<String> names;
    public TrackedActivityAdapter(Context c, ArrayList<String> names)
    {
        this.c=c;
        this.names=names;
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
    public View getView(final  int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.line_category,null);
        }
        TextView t=view.findViewById(R.id.category);
        t.setText(names.get(i));
        CardView cardView=view.findViewById(R.id.card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(c,TrackedShowActivity.class);
                ii.putExtra("sub",names.get(i));
                c.startActivity(ii);

            }
        });
        return view;
    }
}
