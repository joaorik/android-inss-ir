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

        EditText edtDep = (EditText) findViewById(R.id.edtDep);

        double vlBruto = Double.parseDouble(edtSal.getText().toString());

        double vlSemInss = getINSS(vlBruto);

        double vlLiquido = vlBruto - getINSS(vlBruto) - getIR(vlSemInss);

        double vlBrutoINSS = vlBruto - getINSS(vlBruto);

        double vlIR = getIR(vlBrutoINSS);

        int dependent = Integer.parseInt(edtDep.getText().toString());

        double vlDesconto = getDependente(vlBruto, dependent);

        TextView txtSal = (TextView) findViewById(R.id.txtSal);

        NumberFormat textoFormatado = NumberFormat.getCurrencyInstance();

        txtSal.setText("INSS: " + textoFormatado.format(vlSemInss) + "\n\r"
                + "IR: " + textoFormatado.format(vlIR) + "\n\r"
                + "Salário Líquido: " + textoFormatado.format(vlLiquido) + "\n\r"
                + "Desconto dependente: " + textoFormatado.format(vlDesconto));
    }

    public double getINSS(double vlBruto) {

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

    public double getIR(double vlInss) {

        double result = 0;

        if (vlInss > 0.0 && vlInss <= 1903.98) {
            result = 0;
        } else if (vlInss >= 1903.99 && vlInss <= 2826.65) {
            result = ((vlInss * 7.5)/100)-142.80;
        } else if (vlInss >= 3751.66 && vlInss <= 3751.05) {
            result = ((vlInss * 15)/100)-354.80;
        } else if (vlInss >= 3751.06 && vlInss <= 4664.68) {
            result = ((vlInss * 22.5)/100)-636.13;
        } else if (vlInss >= 4664.69 && vlInss >= 4664.68) {
            result = ((vlInss * 27.5)/100)-869.36;
        }

        return result;
    }

    public double getDependente(double salario, int dependente) {

        double result = 0;

        if (salario <= 877.67) {
            result = dependente * 45.00;
        } else if (salario >= 877.68 && salario <= 1319.18) {
            result = dependente * 31.71;
        }

        return result;
    }

}
