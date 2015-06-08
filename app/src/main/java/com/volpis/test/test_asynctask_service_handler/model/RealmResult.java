package com.volpis.test.test_asynctask_service_handler.model;

import io.realm.RealmObject;

/**
 * Created by nazar on 08.06.15.
 */
public class RealmResult extends RealmObject {


    private String url;
    private String title;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
