package com.example.example;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity  {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private String Success,xname,image,productxname,xcode;
    int i;

    // Movies json url
    private static final String url = "http://ios.mysterylenses.com/Service.asmx/getData_JSON?strQuery=exec%20sp_show_detail_items_android";
    private ProgressDialog pDialog;
    private List<GS_Images> ItemList = new ArrayList<GS_Images>();
    private ListView listView;
    private CustomListAdapter adapter;
    GS_Images gs_images = new GS_Images();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, ItemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gs_images = ItemList.get(position);
                Intent i = new Intent(MainActivity.this,SingleItems.class);
                i.putExtra("xname",gs_images.getXname());
                i.putExtra("image",gs_images.getImage());
                i.putExtra("xcode",gs_images.getXcode());
                i.putExtra("productxname",gs_images.getProductxname());
                startActivity(i);
            }

        });
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


        // Creating volley request obj
        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET,url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        String result = response.toString();

                        try {
                            JSONObject obj = new JSONObject(result);
                            Success = obj.getString("success");
                            JSONArray row = obj.getJSONArray("row");
                            for (i = 0; i < row.length(); i++) {
                                JSONObject obj2 = row.getJSONObject(i);
                                GS_Images movie = new GS_Images();
                                xname="xname:"+ obj2.getString("xname");
                                movie.setXname(xname);
                                image=obj2.getString("image_path");
                                movie.setImage(image);
                                productxname="productxcode:"+obj2.getString("productxname");
                                movie.setProductxname(productxname);
                                xcode="xcode"+obj2.getString("xcode");
                                movie.setXcode(xcode);
                                ItemList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            hidePDialog();

        }
    });

    // Adding request to request queue
    AppController.getInstance().addToRequestQueue(movieReq);
}

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }




}

