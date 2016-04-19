package com.cep.cursoandroid.calculadora2016;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    char operacion='0';  // 0 ninguna, 1 +, 2 -,3 / y 4 *
    boolean cambiarDisplay=true, priOpe=true,divCero=false,division=false;

    double res=0;
    char charBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void operar(View v) {
        if (resultadoDisplay()==0 && division)
        {
            inicializar(v);
            TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
            String strdiv0 = getResources().getString(R.string.div0);
            tvDisplay.setText(strdiv0);
            divCero=true;
            return;
        }
        division=false;
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
                division=true;
                if (resultadoDisplay()!=0) {
                    res /= resultadoDisplay();
                }
                else
                {
                    inicializar(v);
                    TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
                    //tvDisplay.setText("division por 0"); //
                    String strdiv0 = getResources().getString(R.string.div0);
                    tvDisplay.setText(strdiv0);
                    divCero=true;
                }
                break;
        }
    }

    public void botonIgual(View v)
    {
        operar(v);
        if (!divCero) {
            String resultado = Double.toString(res);
            if (resultado.length() > 13)
                resultado = "Desbordamiento";
            TextView tvDisplay = (TextView) findViewById(R.id.tvVisor);
            tvDisplay.setText(resultado);
        }
        inicializar(v);
    }

    public void botonesEnabled(View v, boolean valor)
    {
        Button btmas=(Button) findViewById(R.id.btMas);
        Button btmenos=(Button) findViewById(R.id.btMenos);
        Button btdiv=(Button) findViewById(R.id.btDiv);
        Button btpor=(Button) findViewById(R.id.btPor);
        Button btigual=(Button) findViewById(R.id.btIgual);
        btmas.setEnabled(valor);
        btmenos.setEnabled(valor);
        btdiv.setEnabled(valor);
        btpor.setEnabled(valor);
        btigual.setEnabled(valor);

    }
    public void inicializar(View v)
    {
        botonesEnabled(v, false);
        res=0;
        cambiarDisplay=true;
        priOpe=true;
        divCero=false;
    }

    public void botonBorrado(View v)
    {
        TextView tvDisplay=(TextView) findViewById(R.id.tvVisor);
        tvDisplay.setText("0"); //
        inicializar(v);
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
            operar(v);
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
       // if (strDisplay.equals("division por 0"))
         //   botonIgual(v);
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
        botonesEnabled(v,true);

    }
}
