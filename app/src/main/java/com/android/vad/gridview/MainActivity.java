package com.android.vad.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.android.vad.gridview.adapter.MyGridAdapter;
import com.android.vad.gridview.model.ImageObject;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyGridAdapter myGridAdapter;
    private static String DATA_URL = "https://dl.dropboxusercontent.com/u/31298901/demo/demo_image.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        getData();
    }


    private void getData(){

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray allImageArray = jsonObject.optJSONArray("images");
                    if(allImageArray != null && allImageArray.length() > 0){

                        ArrayList<ImageObject> imageObjects = new ArrayList<>();
                        for(int i = 0; i < allImageArray.length();i++){
                            JSONObject jsonItem = allImageArray.optJSONObject(i);

                            imageObjects.add(new ImageObject(jsonItem));
                        }

                        myGridAdapter= new MyGridAdapter(MainActivity.this, imageObjects);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gridView.setAdapter(myGridAdapter);
                            }
                        });
                    }
                } catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}
