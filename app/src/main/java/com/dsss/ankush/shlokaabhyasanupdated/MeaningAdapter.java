package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MeaningAdapter extends BaseAdapter {
    Context c;
    ArrayList<String> l,m;
    public MeaningAdapter(Context c, ArrayList<String> lin,ArrayList<String> m)
    {
        l=lin;
        this.m=m;
        this.c=c;
    }
    @Override
    public int getCount() {
        return m.size();
    }

    @Override
    public Object getItem(int i) {
        return l.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.meaningline,null);
            TextView line=(TextView)view.findViewById(R.id.hymnlinm);
            TextView meaning=(TextView)view.findViewById(R.id.meaningline);
            line.setText(l.get(i));
            meaning.setText(m.get(i));
        }
        return view;
    }
}
