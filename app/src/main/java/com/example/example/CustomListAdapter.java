package com.example.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by AFF22 on 06-Jul-16.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<GS_Images> gs_imagesList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    GS_Images m;

    public CustomListAdapter(Activity activity, List<GS_Images> movieItems) {
        this.activity = activity;
        this.gs_imagesList = movieItems;
    }

    @Override
    public int getCount() {
        return gs_imagesList.size();
    }

    @Override
    public Object getItem(int location) {
        return gs_imagesList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activitymain_items, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView image = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.textView);

        // getting movie data for the row
         m = gs_imagesList.get(position);

        // thumbnail image
        image.setImageUrl(m.getImage(), imageLoader);

        // title
        title.setText(m.getXname());



        return convertView;
    }


}