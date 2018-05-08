package br.usjt.starke.starkesenhas.model;

import android.content.Context;

public class AsyncTaskParams {
    String url;
    Context context;

    public AsyncTaskParams(Context context,String url) {
        this.url = url;
        this.context = context;
    }
}
