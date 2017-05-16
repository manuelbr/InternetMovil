package com.example.manuel.batteryadvisor;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Menu extends AppCompatActivity {
    private View.OnClickListener listenerBoton1, listenerBoton2, listenerBoton3;
    private HashMap<Integer,Pair<Integer,Integer>> mediciones;
    private static boolean status;
    private static Intent servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listenerBoton1 = new ListenerButtonMenu(this,"servicioOn");
        listenerBoton2 = new ListenerButtonMenu(this,"servicioOff");
        listenerBoton3 = new ListenerButtonMenu(this,"estadisticas");
        status = false;

        //Se crean los botones y se les asignan sus correspondientes actuadores
        Button begin = (Button)findViewById(R.id.begin);
        begin.setOnClickListener(listenerBoton1);
        Button stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(listenerBoton2);
        Button estadisticas = (Button)findViewById(R.id.estadisticas);
        estadisticas.setOnClickListener(listenerBoton3);
        ///////////////////////////////////////////////////////////////////////

        //Se lee la colección de archivos de la base de datos
        try {
            loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Servicio.setColeccion(mediciones);
        Servicio.setContext(this);

    }

    public void loadFile() throws IOException {
        HashMap<Integer,Pair<Integer,Integer>> mediciones = new HashMap<Integer,Pair<Integer,Integer>>();
        File db = new File(this.getFilesDir()+"/mediciones.txt");

        if(!db.exists()){
            db.createNewFile();
        }

        //Lectura del archivo
        try {
            BufferedReader br = new BufferedReader(new FileReader(db));
            String line;
            int medicion, numMediciones, i = 0;
            String[] lineSplit;

            while ((line = br.readLine()) != null) {
                if(!line.toString().equals("")){
                    lineSplit = line.split(" ");
                    medicion = Integer.parseInt(lineSplit[0]);
                    numMediciones = Integer.parseInt(lineSplit[1]);
                    mediciones.put(i,new Pair(medicion,numMediciones));
                    i++;
                }
            }
            br.close();
        }
        catch (IOException e) {
        }

        if(mediciones.size() == 0){
            mediciones = new HashMap<Integer,Pair<Integer,Integer>>();

            for(int j = 0; j<144; j++){
                mediciones.put(j,new Pair(0,0));
            }

        }

        this.mediciones = mediciones;
    }

    public static void setStatus(boolean estado){
        status = estado;
    }

    public static void setService(Intent service){
        servicio = service;
    }

    public static boolean getStatus(){
        return status;
    }

    public static Intent getServicio(){
        return servicio;
    }
}
