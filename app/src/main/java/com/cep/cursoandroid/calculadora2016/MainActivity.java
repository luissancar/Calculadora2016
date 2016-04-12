package com.cep.cursoandroid.calculadora2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    char operacion='0';  // 0 ninguna, 1 +, 2 -,3 / y 4 *
    boolean cambiarDisplay=true, priOpe=true;

    double res=0;
    char charBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void operar() {
        switch (charBoton) { 
            case ('+'):
                res += resultadoDisplay();
                break;
            case ('-'):
                res = res - resultadoDisplay();
                break;
            case ('X'):
                res *= resultadoDisplay();
                break;
            case ('/'):
                res /= resultadoDisplay();
                break;
        }
    }

    public void botonIgual(View v)
    {
        operar();



        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        tvDisplay.setText(new Double(res).toString());

        res=0.0;
        //resultadoDisplay();
        cambiarDisplay=true;
        priOpe=true;







    }


    public void botonBorrado(View v)
    {
        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        res=0;
        tvDisplay.setText("0"); //
        cambiarDisplay=true;
        priOpe=true;
    }

    public void botonOperador(View v)
    {

        Button boton =(Button) v;// saber el boton: su ID  getID()
        charBoton=boton.getText().charAt(0);
        cambiarDisplay=true;
        if (priOpe) {
            priOpe = false;
            res = resultadoDisplay();
        }
        else
            operar();
        //res=resultadoDisplay();
                //TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
                //tvDisplay.setText(new Character(charBoton).toString());





    }


    double resultadoDisplay()
    {
        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        String strDisplay=tvDisplay.getText().toString();
        return new Double(strDisplay).doubleValue();
    }
    public void botonNumerico(View v)
    {
        Button boton =(Button) v;// saber el boton: su ID  getID()
        // saber el texto boton  getText()
        String strBoton=boton.getText().toString();
        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        String strDisplay=tvDisplay.getText().toString();
        // recuperar texto visor
        if (cambiarDisplay) {
            strDisplay = strBoton;
          //  botonIgual(v);
            cambiarDisplay = false;
        }
        else
            strDisplay+=strBoton;
        // concatenar strVisort + strBoton
        tvDisplay.setText(strDisplay);
        // Guardar nuevo texto en el visor  setText(

    }
}
