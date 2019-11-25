package com.dsss.ankush.shlokaabhyasanupdated;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dsss.ankush.shlokaabhyasanupdated.CategoryType;
import com.dsss.ankush.shlokaabhyasanupdated.R;
import com.dsss.ankush.shlokaabhyasanupdated.SubCategoryActivity;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    ArrayList<String> names;
    ArrayList<CategoryType> categoryTypes;
    Context c;

    public MyAdapter(Context c, ArrayList<String> names,ArrayList<CategoryType> categoryTypes)
    {
        this.names=names;
        this.categoryTypes=categoryTypes;
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
                Intent ii=new Intent(c,SubCategoryActivity.class);
                ii.putExtra("sub",categoryTypes.get(i).name);
              //  ii.putExtra("object",categoryTypes.get(i));
                c.startActivity(ii);
            }
        });
        return view;
    }
}
