package com.example.hayawanpedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<String> idList;
    private ArrayList<String> indoList;
    private ArrayList<String> arabList;
    private ArrayList<String> penjelasanList;
    private ArrayList<String> fotoList;
    private Context context;

    public ListViewAdapter(ArrayList<String> idList, ArrayList<String> indoList, ArrayList<String> arabList,
                           ArrayList<String> penjelasanList, ArrayList<String> fotoList, Context context) {
        this.idList = idList;
        this.indoList = indoList;
        this.arabList = arabList;
        this.penjelasanList = penjelasanList;
        this.fotoList = fotoList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int position) {
        return idList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // If your data has a unique identifier for each item, you can return it here.
        // Otherwise, you can simply return the position as the item ID.
        return position;
    }

    public String getId(int position) {
        return idList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.indoTextView = convertView.findViewById(R.id.hewan);
//            viewHolder.arabTextView = convertView.findViewById(R.id.hArab);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set the data to the views in the list item
        viewHolder.indoTextView.setText(indoList.get(position));
//        viewHolder.arabTextView.setText(arabList.get(position));

        return convertView;
    }

    private static class ViewHolder {
        TextView indoTextView;
//        TextView arabTextView;
    }
}
