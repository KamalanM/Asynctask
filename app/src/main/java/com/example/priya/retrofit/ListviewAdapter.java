package com.example.priya.retrofit;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListviewAdapter extends ArrayAdapter {

    List list=new ArrayList();


    public ListviewAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Display object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View row;
        row=convertView;
        Adapter adapter;
        if(convertView==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            adapter=new Adapter();
            adapter.name=(TextView) row.findViewById(R.id.name);
            row.setTag(adapter);
        }
        else
        {
            adapter=(Adapter)row.getTag();
        }

        Display display=(Display) this.getItem(position);
        adapter.name.setText(display.getName());

        return row;
    }


    static class Adapter {
        TextView name;
    }
}
