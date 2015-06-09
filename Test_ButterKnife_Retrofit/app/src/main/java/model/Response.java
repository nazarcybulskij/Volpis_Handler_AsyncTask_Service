package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nazar on 09.06.15.
 */
public class Response {


    @SerializedName("origin")
    private  String originIP;
    @SerializedName("url")
    private  String url;

    private Argumention args;



    public String getOriginIP() {
        return originIP;
    }

    public void setOriginIP(String originIP) {
        this.originIP = originIP;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Response{\n" + "opigin = " + originIP + ",\nurl = " + url+"\n" +
                args.toString()+
                "}";

    }






}
