package com.example.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by AFF22 on 06-Jul-16.
 */
public class SingleItems extends Activity {
    String xname, xcode,productxname,image;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_items);
        Intent i = getIntent();
        // Get the result of rank
        xname = i.getStringExtra("xname");
        // Get the result of country
        xcode = i.getStringExtra("xcode");
        // Get the result of population
        productxname = i.getStringExtra("productxname");
        // Get the result of flag
        image = i.getStringExtra("image");
        TextView txtxname = (TextView) findViewById(R.id.textView2);
        TextView txtxcode = (TextView) findViewById(R.id.textView3);
        TextView txtproductxcode = (TextView) findViewById(R.id.textView4);

        // Locate the ImageView in singleitemview.xml
        NetworkImageView imgflag = (NetworkImageView) findViewById(R.id.imageView);
        txtxname.setText(xname);
        txtxcode.setText(xcode);
        txtproductxcode.setText(productxname);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imgflag.setImageUrl(image, imageLoader);
    }
}
