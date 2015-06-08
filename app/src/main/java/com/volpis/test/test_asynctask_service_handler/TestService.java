package com.volpis.test.test_asynctask_service_handler;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.volpis.test.test_asynctask_service_handler.db.DB;
import com.volpis.test.test_asynctask_service_handler.db.DBHelper;
import com.volpis.test.test_asynctask_service_handler.model.GoogleResults;
import com.volpis.test.test_asynctask_service_handler.model.RealmResult;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by nazar on 08.06.15.
 */
public class TestService extends Service {

    Realm realm;
    DB db;



    @Override
    public void onCreate() {
        super.onCreate();
        db = new DB(this);
        db.open();

        //mDBHelper = new DBHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendRequest(intent);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void sendRequest(final Intent intent){


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       // mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);





        new Thread(new Runnable() {
            @Override
            public void run() {
                String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
                String search = intent.getStringExtra("search");
                String charset = "UTF-8";
                InputStream inputStream = null;
                //String result = "";
                if (search==null){
                    search="Google";
                }

                String url = null;
                try {
                    url = google + URLEncoder.encode(search, charset);


                    // create HttpClient
                    HttpClient httpclient = new DefaultHttpClient();

                    // make GET request to the given URL
                    HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

                    // receive response as inputStream
                    inputStream = httpResponse.getEntity().getContent();

                    Reader reader = new InputStreamReader(inputStream, charset);
                    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
                    List<GoogleResults.Result> list=results.getResponseData().getResults();


                    realm = Realm.getInstance(TestService.this);

                    realm.beginTransaction();

                    for (GoogleResults.Result temp:list){
                        //SqlLite
                        db.add(temp);



                        //Realm DB
                       RealmResult dbResult = realm.where(RealmResult.class)
                                            .equalTo("url",temp.getUrl())
                                            .findFirst();

                        if (dbResult==null) {
                            RealmResult result = realm.createObject(RealmResult.class);
                            result.setUrl(temp.getUrl());
                            result.setTitle(temp.getTitle());
                        }else{
                            dbResult.setUrl(temp.getUrl());
                            dbResult.setTitle(temp.getTitle());
                        }
                    }

                    realm.commitTransaction();


//                    RealmQuery<RealmResult> query = realm.where(RealmResult.class);
//                    RealmResults<RealmResult> resultInsert = query.findAll();
                   // Log.v("Tag",resultInsert.toString());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }).start();
    }


//    public void insertDB(GoogleResults.Result result){
//        ContentValues cv = new ContentValues();
//        SQLiteDatabase db = mDBHelper.getWritableDatabase();
//
//        cv.put("url", result.getUrl());
//        cv.put("title", result.getTitle());
//
//        long rowID = db.insert("testtable", null, cv);
//    }
//
//    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
//        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null)
//            result += line;
//
//        inputStream.close();
//        return result;
//
//    }
}
