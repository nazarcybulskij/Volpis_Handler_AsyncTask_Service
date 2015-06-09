package com.volpis.test.test_butterknife_retrofit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import RetrofitService.HttpbinService;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import model.Response;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.button_error)
    Button mBError;
    @InjectView(R.id.button_get)
    Button mBGet;
    @InjectView(R.id.button_post)
    Button mBPost;
    @InjectView(R.id.textview_response)
    TextView mTVResponse;

    RestAdapter restAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://httpbin.org")
                .build();

    }




    @OnClick(R.id.button_error)
    public void postButtonclick(View view) {
        HttpbinService service = restAdapter.create(HttpbinService.class);
        service.getErrorResponse(new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                mTVResponse.setText(response.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                mTVResponse.setText(error.getMessage());


            }
        });
    }

    @OnClick(R.id.button_post)
    public void errorButtonclick(View view) {

        HttpbinService service = restAdapter.create(HttpbinService.class);
        service.getPOSTResponse(new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                Toast.makeText(MainActivity.this, "POST", Toast.LENGTH_SHORT).show();
                mTVResponse.setText(response.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    @OnClick(R.id.button_get)
    public void getButtonclick(View view) {

        HttpbinService service = restAdapter.create(HttpbinService.class);
        service.getGETResponse(new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                Toast.makeText(MainActivity.this,"GET",Toast.LENGTH_SHORT).show();
                mTVResponse.setText(response.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        //Toast.makeText(this,"GET",Toast.LENGTH_SHORT).show();

    }





}
