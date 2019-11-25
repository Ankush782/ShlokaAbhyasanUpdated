package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DownloadActivityAdapter extends BaseAdapter {
    ArrayList<String> names,data,meanings;
    Context c;
    DownloadActivityAdapter(Context c,ArrayList<String> n,ArrayList<String> d,ArrayList<String> m)
    {
        this.c=c;
        names=n;
        meanings=m;
        this.data=d;
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
            view= LayoutInflater.from(c).inflate(R.layout.line_category,null);
        }
        TextView t= view.findViewById(R.id.category);
        t.setText(names.get(i));
        final CardView cc=view.findViewById(R.id.card);
        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(c,DownloadShowActivity.class);
                ii.putExtra("name",names.get(i));
                ii.putExtra("data",data.get(i));
                ii.putExtra("meaning",meanings.get(i));
                c.startActivity(ii);




            }
        });
        return view;
    }
}
