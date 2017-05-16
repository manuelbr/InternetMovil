package com.example.manuel.batteryadvisor;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Manuel on 02/05/2017.
 */

public class ListenerButtonMenu implements View.OnClickListener{
    private String accion;
    private static boolean status;
    private Context context;
    private Intent serviceIntent;

    public ListenerButtonMenu(Context contxt, String action){
        accion = action;
        context = contxt;

    }

    @Override
    public void onClick(View v) {
        switch(accion){
            case "servicioOn": if(!Menu.getStatus()) {
                                    serviceIntent = new Intent(context, Servicio.class);
                                    Toast.makeText(context, "El servicio está en marcha", Toast.LENGTH_LONG).show();
                                    Menu.setStatus(true);
                                    Menu.setService(serviceIntent);
                                    context.startService(serviceIntent);
                                }else{
                                    Toast.makeText(context, "El servicio ya está en marcha", Toast.LENGTH_LONG).show();
                                }
                                break;
            case "servicioOff": if(!Menu.getStatus()){
                                    Toast.makeText(context, "El servicio no está en marcha", Toast.LENGTH_LONG).show();
                                }else {
                                    context.stopService(Menu.getServicio());
                                    try {
                                        writeMediciones();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Menu.setStatus(false);
                                    Toast.makeText(context, "Se ha parado el servicio", Toast.LENGTH_LONG).show();
                                }
                                break;
            case "estadisticas": Intent intent = new Intent(context, Estadisticas.class);
                                 context.startActivity(intent);
                                 break;
        }
    }

    public void writeMediciones() throws IOException {
        HashMap<Integer,Pair<Integer,Integer>> mediciones = Servicio.getColeccion();

        File db = new File(context.getFilesDir()+"/mediciones.txt");

        FileOutputStream fOut = new FileOutputStream(db);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

        for (int i = 0; i < mediciones.size(); i++) {
            myOutWriter.write(mediciones.get(i).first.toString()+" "+mediciones.get(i).second.toString()+"\n\r");
        }

        myOutWriter.close();
        fOut.close();
    }
}
