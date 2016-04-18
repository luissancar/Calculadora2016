package com.cep.cursoandroid.calculadora2016;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    char operacion='0';  // 0 ninguna, 1 +, 2 -,3 / y 4 *
    boolean cambiarDisplay=true, priOpe=true,divCero=false;

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
                if (resultadoDisplay()!=0) {
                    res /= resultadoDisplay();
                }
                else
                {
                    cambiarDisplay=true;
                    priOpe=true;
                    res=0;
                    TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
                    tvDisplay.setText("division por 0"); //
                    divCero=true;
                }
                break;
        }
    }

    public void botonIgual(View v)
    {
        operar();
        if (!divCero) {
            String resultado = new Double(res).toString();
            if (resultado.length() > 13)
                resultado = "Desbordamiento";
            TextView tvDisplay = (TextView) findViewById(R.id.tvVisor);
            tvDisplay.setText(resultado);
        }

        res=0;
        //resultadoDisplay();
        cambiarDisplay=true;
        priOpe=true;
        divCero=false;







    }


    public void botonBorrado(View v)
    {
        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        res=0;
        tvDisplay.setText("0"); //
        cambiarDisplay=true;
        priOpe=true;
        divCero=false;
    }

    public void botonOperador(View v)
    {

        Button boton =(Button) v;// saber el boton: su ID  getID()
        charBoton=boton.getText().charAt(0);
        if (cambiarDisplay) {
            return;
        }
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

        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        String strDisplay=tvDisplay.getText().toString();
        if (strDisplay.length()>12 && !cambiarDisplay)
            return;
        if (strDisplay.equals("division por 0"))
            botonIgual(v);
        Button boton =(Button) v;// saber el boton: su ID  getID()
        // saber el texto boton  getText()
        String strBoton=boton.getText().toString();
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
