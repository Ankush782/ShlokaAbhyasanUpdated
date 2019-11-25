package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dsss.ankush.shlokaabhyasanupdated.HymnShow;
import com.dsss.ankush.shlokaabhyasanupdated.R;

import java.util.ArrayList;

import com.dsss.ankush.shlokaabhyasanupdated.HymnType;

public class HymnsAdapter extends BaseAdapter {
    ArrayList<String> names;
    ArrayList<HymnType> data;
    Context c;
    HymnsAdapter(Context c, ArrayList<String> names,ArrayList<HymnType> data )
    {
        this.names=names;
        this.data=data;
        this.c=c;
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
        TextView t=view.findViewById(R.id.category);
        t.setText(names.get(i));
        CardView cardView=view.findViewById(R.id.card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(c,HymnShow.class);
                ii.putExtra("sub",names.get(i));
                ii.putExtra("object",data.get(i));
                c.startActivity(ii);
            }
        });
        return view;

    }
}
