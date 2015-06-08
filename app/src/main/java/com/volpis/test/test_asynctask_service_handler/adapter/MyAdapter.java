package com.volpis.test.test_asynctask_service_handler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.volpis.test.test_asynctask_service_handler.model.RealmResult;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class MyAdapter extends RealmBaseAdapter<RealmResult> implements ListAdapter {

    private static class ViewHolder {
        TextView title;
    }

    public MyAdapter(Context context, int resId, RealmResults<RealmResult> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RealmResult item = realmResults.get(position);

        //update item
        viewHolder.title.setText(item.getTitle());


        return convertView;
    }

    public RealmResults<RealmResult> getRealmResults() {
        return realmResults;
    }
}