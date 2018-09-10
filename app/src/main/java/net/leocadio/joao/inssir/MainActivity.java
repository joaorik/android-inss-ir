package net.leocadio.joao.inssir;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtCalc(View view) {

        EditText edtSal = (EditText) findViewById(R.id.edtSal);

        double vlBruto = Double.parseDouble(edtSal.getText().toString());

        double vlSemInss = getINSS(vlBruto);

        double vlLiquido = vlBruto - getINSS(vlBruto) - getIR(vlSemInss);

        double vlIR = getIR(vlBruto);

        TextView txtSal = (TextView) findViewById(R.id.txtSal);

        NumberFormat textoFormatado = NumberFormat.getCurrencyInstance();

        txtSal.setText("INSS: "+ textoFormatado.format(vlSemInss) + "\n\r");
        txtSal.setText("IR: "+ textoFormatado.format(vlIR) + "\n\r");
        txtSal.setText("Salário Líquido: "+ textoFormatado.format(vlLiquido) + "\n\r");
    }

    public double getINSS(double vlBruto){

        double result = 0;

        if (vlBruto <= 1693.72) {
            result = (vlBruto * 8)/100;
        } else if (vlBruto <= 2822.90) {
            result = (vlBruto * 9)/100;
        } else if (vlBruto <= 5645.80) {
            result = (vlBruto * 11)/100;
        } else {
            result = (5645.80 * 11)/100;
        }

        return result;
    }

    public double getIR(double vlSemInss){

        double result = 0.0;

        if (vlSemInss > 0.00 && vlSemInss <= 1903.98) {
            result = 0;
        } else if (vlSemInss >= 1903.99 && vlSemInss <= 2826.65) {
            result = ((vlSemInss * 7.5)/100)-142.80;
        } else if (vlSemInss >= 2826.66 && vlSemInss <= 3751.05) {
            result = ((vlSemInss * 15)/100)-354.80;
        } else if (vlSemInss >= 3751.06 && vlSemInss <= 4664.68) {
            result = ((vlSemInss * 22.5)/100)-636.13;
        } else {
            result = ((vlSemInss * 27.5)/100)-869.36;
        }

        return result;
    }
}
