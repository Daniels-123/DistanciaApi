package com.digitalandroidyweb.distanciaapi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GeoTarea.Geo {

    EditText lugar1, lugar2;
    Button btn_calcular;

    String str_form, str_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declarar();


        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    str_form=lugar1.getText().toString().trim();
                    str_to=lugar2.getText().toString().trim();
                    String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + str_form + "&destinations=" + str_to + "&mode=driving&language=fr-FR&avoid=tolls&key=AIzaSyCx3X-rLpRehD7utVBiDqNk6heMKocWKi4";
                    new GeoTarea(MainActivity.this).execute(url);


            }
        });
    }


    public void setDouble(String result) {
        String res[]=result.split(",");
        Double min=Double.parseDouble(res[0])/60;
        int dist=Integer.parseInt(res[1])/1000;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Importante");
        builder.setMessage( "DISTANCIA= " + dist + " kilometros" + "\n" + "DURACION= " + (int) (min / 60) + " hr " + (int) (min % 60) + " mins");

        builder.setPositiveButton("OK",null);
        builder.create();
        builder.show();

    }

    private void declarar() {
        lugar1 = findViewById(R.id.lugar1);
        lugar2 = findViewById(R.id.lugar2);
        btn_calcular = findViewById(R.id.btn_calcular);

    }
}
