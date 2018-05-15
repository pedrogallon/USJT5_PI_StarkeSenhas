package br.usjt.starke.starkesenhas.model;

import android.content.Context;

public class AsyncTaskParams {
    String url;
    Context context;

    public AsyncTaskParams(Context context,String url) {
        this.url = url;
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
