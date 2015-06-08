package com.volpis.test.testvolley;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity  implements View.OnClickListener{
    
    Button mBGet;
    Button mBPost;
    Button mB404;
    Button mBPut;
    Button mBDelete;
    Button mBHtml;
    Button mBStreem;

    ProgressBar mPBGet;
    ProgressBar mPBPost;
    ProgressBar mPB404;
    ProgressBar mPBPut;
    ProgressBar mPBDelete;
    ProgressBar mPBHtml;
    ProgressBar mPBStreem;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBGet = (Button)findViewById(R.id.get);
        mBPost = (Button)findViewById(R.id.post);
        mB404 = (Button)findViewById(R.id.status404);
        mBPut = (Button)findViewById(R.id.put);
        mBDelete = (Button)findViewById(R.id.delete);
        mBHtml = (Button)findViewById(R.id.html);
        mBStreem = (Button)findViewById(R.id.streamByte);


        mPBGet = (ProgressBar)findViewById(R.id.Pget);
        mPBPost = (ProgressBar)findViewById(R.id.Ppost);
        mPB404 = (ProgressBar)findViewById(R.id.Pstatus404);
        mPBPut = (ProgressBar)findViewById(R.id.Pput);
        mPBDelete = (ProgressBar)findViewById(R.id.Pdelete);
        mPBHtml = (ProgressBar)findViewById(R.id.Phtml);
        mPBStreem = (ProgressBar)findViewById(R.id.PstreamByte);


         mBGet.setOnClickListener(this);
         mBPost.setOnClickListener(this);
         mB404.setOnClickListener(this);
         mBPut.setOnClickListener(this);
         mBDelete.setOnClickListener(this);
         mBHtml.setOnClickListener(this);
         mBStreem.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.get:
                requestGet();
                break;
            case R.id.post:
                requestPost();
                break;
            case R.id.delete:
                requestDelete();
                break;
            case R.id.put:
                requestPut();
                break;
            case R.id.status404:
                requeststaus404();
                break;
            case R.id.streamByte:
                requestStreambyte();
                break;
            case R.id.html:
                requestHtml();
                break;



        }

    }


    //  я  знаю що дублювання коду   погана практика ,  але  (((


    public void requestGet(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/get";
        mPBGet.setVisibility(View.VISIBLE);
        mBGet.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPBGet.setVisibility(View.INVISIBLE);
                        mBGet.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPBGet.setVisibility(View.INVISIBLE);
                mBGet.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
    public void requestPost(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/post";
        mPBPost.setVisibility(View.VISIBLE);
        mBPost.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPBPost.setVisibility(View.INVISIBLE);
                        mBPost.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPBPost.setVisibility(View.INVISIBLE);
                mBPost.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void requestDelete(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/delete";
        mPBDelete.setVisibility(View.VISIBLE);
        mBDelete.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPBDelete.setVisibility(View.INVISIBLE);
                        mBDelete.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPBDelete.setVisibility(View.INVISIBLE);
                mBDelete.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void requestPut(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/put";
        mPBPut.setVisibility(View.VISIBLE);
        mBPut.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPBPut.setVisibility(View.INVISIBLE);
                        mBPut.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPBPut.setVisibility(View.INVISIBLE);
                mBPut.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    public void requeststaus404(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/status/404";
        mPB404.setVisibility(View.VISIBLE);
        mB404.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPB404.setVisibility(View.INVISIBLE);
                        mB404.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPB404.setVisibility(View.INVISIBLE);
                mB404.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    public void requestStreambyte(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/stream-bytes/:n";
        mPBStreem.setVisibility(View.VISIBLE);
        mBStreem.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPBStreem.setVisibility(View.INVISIBLE);
                        mBStreem.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPBStreem.setVisibility(View.INVISIBLE);
                mBStreem.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


    public void requestHtml(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://httpbin.org/html";
        mPBHtml.setVisibility(View.VISIBLE);
        mBHtml.setEnabled(false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Response is: " + response.toString(), Toast.LENGTH_SHORT).show();
                        mPBHtml.setVisibility(View.INVISIBLE);
                        mBHtml.setEnabled(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_SHORT).show();
                mPBHtml.setVisibility(View.INVISIBLE);
                mBHtml.setEnabled(true);


            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }




}
