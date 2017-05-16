package com.example.manuel.batteryadvisor;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Manuel on 09/05/2017.
 */

public class BatteryAdvisor implements Runnable {
    private String tex;
    private Context mContext;

    public BatteryAdvisor(Context c, String texto) {
        tex = texto;
        mContext = c;
    }

    @Override
    public void run() {
        Toast.makeText(mContext, tex, Toast.LENGTH_SHORT).show();
    }
}
