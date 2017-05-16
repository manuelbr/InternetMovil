package com.example.manuel.batteryadvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.util.Log;
import android.widget.AnalogClock;
import java.util.ArrayList;
import java.util.HashMap;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        //Definición de los botones estadísticos
        HashMap<Integer, android.util.Pair<Integer,Integer>> medidas = Servicio.getColeccion();
        ArrayList<Integer> valores = new ArrayList<Integer>();

        //Reloj dinámico
        AnalogClock analog = (AnalogClock) findViewById(R.id.analog_clock);


        //Se calcula la media de batería de cada hora
        int min,max,minid,maxid, resultado;
        for(int i = 0; i<24; i++){
            min = 100000000;
            max = -100000000;
            minid = 0;
            maxid = 0;

            //Se calcula el mínimo y el máximo de batería medida para cada hora
            for(int j = 0; j<6; j++){
                if((medidas.get(i*6+j).first > max) ){
                    max = medidas.get(i*6+j).first;
                    maxid = (i*6+j);
                }

                if((medidas.get(i*6+j).first < min) && (medidas.get(i*6+j).first != 0)){
                    min = medidas.get(i*6+j).first;
                    minid = (i*6+j);
                }
            }
            //No se ha hayado un mínimo no igual a cero
            if(min == 100000000){
                min = 0;
            }

            if(maxid > minid)
                resultado = min-max;
            else
                resultado = max-min;

            valores.add(resultado);
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        DataPoint[] puntos = new DataPoint[24];

        for(int i = 0; i<24; i++){
            puntos[i] = new DataPoint(i, valores.get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(puntos);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-100);
        graph.getViewport().setMaxY(100);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(25);

        // enable scaling and scrolling
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);

        graph.setTitle("Gasto medio de Batería por hora");
    }

}
