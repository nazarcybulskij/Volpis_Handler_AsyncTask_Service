package com.volpis.test.test_asynctask_service_handler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.volpis.test.test_asynctask_service_handler.adapter.MyAdapter;
import com.volpis.test.test_asynctask_service_handler.db.DB;
import com.volpis.test.test_asynctask_service_handler.model.RealmResult;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,LoaderManager.LoaderCallbacks<Cursor> {


    Button mButtonService;
    Button mButtonAsynckTask;
    Button mButtonHandler;

    ProgressBar mProgressBarHandler;
    ProgressBar mProgressBarAsynckTask;
    ProgressBar mProgressBarService;

    Handler mHandler;

    ListView mListViewService;
    ListView mListViewServiceSQLLite;

    private Realm realm;

    final static int STATUS_NONE = 0;
    final static int STATUS_LOADING = 1;

    DB db;
    SimpleCursorAdapter scAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();

        mButtonHandler = (Button)findViewById(R.id.button_handler_start);
        mButtonService = (Button) findViewById(R.id.button_service_start);
        mButtonAsynckTask = (Button)findViewById(R.id.button_asynctask_start);
        mProgressBarHandler =(ProgressBar)findViewById(R.id.progressbar_handler);
        mProgressBarAsynckTask =(ProgressBar)findViewById(R.id.progressbar_async_task);
        mProgressBarService =(ProgressBar)findViewById(R.id.progressbar_service);
        mListViewService = (ListView)findViewById(R.id.listView_service);
        mListViewServiceSQLLite = (ListView)findViewById(R.id.listView_service_sqllite);

        realm = Realm.getInstance(this);
        RealmResults<RealmResult> listSearch = realm.where(RealmResult.class).findAll();
        final MyAdapter adapter = new MyAdapter(this, R.id.listView_service, listSearch, true);

        mListViewService.setAdapter(adapter);

        mButtonHandler.setOnClickListener(this);
        mButtonAsynckTask.setOnClickListener(this);
        mButtonService.setOnClickListener(this);

        mHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {

                switch (msg.what) {
                    case STATUS_NONE:
                        mProgressBarHandler.setVisibility(View.INVISIBLE);
                        mButtonHandler.setEnabled(true);
                        break;
                    case STATUS_LOADING:
                        mProgressBarHandler.setVisibility(View.VISIBLE);
                        mButtonHandler.setEnabled(false);
                        break;
                }


            }
        };
        mHandler.sendEmptyMessage(STATUS_NONE);

        // формируем столбцы сопоставления
        String[] from = new String[] { DB.COLUMN_TITLE, DB.COLUMN_URL };
        int[] to = new int[] { R.id.title, R.id.url };

        // создааем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0);

        mListViewServiceSQLLite.setAdapter(scAdapter);

        // создаем лоадер для чтения данных
        getSupportLoaderManager().initLoader(0, null, this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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


    public void sendRequest() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.button_handler_start:
                clickHandlerButton();
                break;
            case R.id.button_asynctask_start:
                clickAssyncTaskButton();
                break;
            case R.id.button_service_start:
                clickServiceButton();
                break;

        }

    }

    public void clickHandlerButton(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(STATUS_LOADING);
                sendRequest();
                mHandler.sendEmptyMessage(STATUS_NONE);
            }
        }).start();

    }

    public void clickAssyncTaskButton(){
        new TestAssynckTask().execute();
    }

    public void clickServiceButton(){
        Intent intent=new Intent(MainActivity.this,TestService.class);
        intent.putExtra("search","Volpis");
        startService(intent);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    class TestAssynckTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBarAsynckTask.setVisibility(View.VISIBLE);
            mButtonAsynckTask.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            sendRequest();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressBarAsynckTask.setVisibility(View.INVISIBLE);
            mButtonAsynckTask.setEnabled(true);
        }
    }

    static class MyCursorLoader extends CursorLoader {

        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getAllData();
            return cursor;
        }

    }

}



