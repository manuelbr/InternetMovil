package com.example.manuel.batteryadvisor;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Pair;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Manuel on 02/05/2017.
 */

public class Servicio extends IntentService{
    private static HashMap<Integer,Pair<Integer,Integer>> mediciones;
    private BroadcastReceiver mBatInfoReceiver;
    private boolean sentinel = false;
    private static int antValue = -1;
    private static Context context;

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {

        //Temporizador: Cada 10 minutos se pone la variable sentinel a true y se permite una medición
        //de la batería
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                sentinel = true;
            }
        };
        timer.schedule (hourlyTask, 0l, 1000*60*10);//Cada diez minutos
        /////////////////////////////////////////////////////////////////////////////////////////////

        //Define que se hará cuando se detecte el cambio de batería./////////////////////////////////
        mBatInfoReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context ctxt, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int minuto, hora;
                int cuadrante;

                if(antValue == -1){
                    antValue = level;
                }

                //Si ha pasado el tiempo reglamentario y la variación entre nivel anterior y actual de batería no es muy drástica
                //se guarda el valor.
                if(sentinel && ((antValue - level) < 20) ){

                    sentinel = false;
                    Calendar rightNow = Calendar.getInstance();
                    hora = rightNow.get(Calendar.HOUR_OF_DAY);
                    minuto = rightNow.get(Calendar.MINUTE);
                    cuadrante = minuto/10;

                    if(mediciones.get(hora*6+cuadrante).first != 0 && (Math.abs(mediciones.get(hora*6+cuadrante).first - level) > 20) && (getCurrentTopActivity().equals("com.android.launcher2.Launcher"))){
                        Handler handler = new Handler();
                        handler.post(new BatteryAdvisor(context, "Se está consumiendo la batería de forma inusualmente rápida"));
                    }

                    mediciones.put(hora*6+cuadrante,new Pair((mediciones.get(hora*6+cuadrante).first*mediciones.get(hora*6+cuadrante).second+level)/(mediciones.get(hora*6+cuadrante).second+1),mediciones.get(hora*6+cuadrante).second+1));

                }else
                    if(sentinel && (getCurrentTopActivity().equals("com.android.launcher2.Launcher"))){
                        sentinel = false;
                        Handler handler = new Handler();
                        handler.post(new BatteryAdvisor(context, "Se está consumiendo la batería rápidamente..."));
                    }

                antValue = level;
            }
        };
        /////////////////////////////////////////////////////////////////////////////////////////////
        this.registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mBatInfoReceiver);
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private String getCurrentTopActivity() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
        return ar.topActivity.getClassName().toString();
    }

    public Servicio() {
        super("");
    }

    public static void setColeccion(HashMap<Integer,Pair<Integer,Integer>> col){
        mediciones = col;
    }

    public static void setContext(Context ctxt){
        context = ctxt;
    }

    public static HashMap<Integer,Pair<Integer,Integer>> getColeccion(){
        return mediciones;
    }
}
