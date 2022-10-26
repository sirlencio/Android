package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String n1 = "";
    String n2 = "";
    String signo = "";

    private TextView grande;
    private TextView peque;

    private RadioButton binario;
    private RadioButton decimal;

    private Button buttonDel;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonC;
    private Button buttonPorc;
    private Button buttonDiv;
    private Button buttonMul;
    private Button buttonRes;
    private Button buttonSum;
    private Button buttonIgu;
    private Button buttonComa;
    private Button buttonAND;
    private Button buttonOR;
    private Button buttonXOR;
    private Button buttonNOT;

    private boolean comprobante = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grande = (TextView) findViewById(R.id.textViewGrande);
        peque = (TextView) findViewById(R.id.textViewPeq);

        binario = (RadioButton) findViewById(R.id.binario);
        decimal = (RadioButton) findViewById(R.id.decimal);

        buttonComa = (Button) findViewById(R.id.punto);
        button0 = (Button) findViewById(R.id.cero);
        button1 = (Button) findViewById(R.id.uno);
        button2 = (Button) findViewById(R.id.dos);
        button3 = (Button) findViewById(R.id.tres);
        button4 = (Button) findViewById(R.id.cuatro);
        button5 = (Button) findViewById(R.id.cinco);
        button6 = (Button) findViewById(R.id.seis);
        button7 = (Button) findViewById(R.id.siete);
        button8 = (Button) findViewById(R.id.ocho);
        button9 = (Button) findViewById(R.id.nueve);
        buttonPorc = (Button) findViewById(R.id.porcentaje);
        buttonDiv = (Button) findViewById(R.id.dividir);
        buttonMul = (Button) findViewById(R.id.por);
        buttonRes = (Button) findViewById(R.id.menos);
        buttonSum = (Button) findViewById(R.id.mas);
        buttonDel = (Button) findViewById(R.id.borrar);
        buttonC = (Button) findViewById(R.id.eliminar);
        buttonIgu = (Button) findViewById(R.id.igual);
        buttonAND = (Button) findViewById(R.id.AND);
        buttonOR = (Button) findViewById(R.id.OR);
        buttonXOR = (Button) findViewById(R.id.XOR);
        buttonNOT = (Button) findViewById(R.id.NOT);

        //Cambio de modo
        decimal.setOnClickListener(view -> {
            decimal.setSelected(true);
            binario.setSelected(false);
            if (comprobante) {
                comprobante = false;
                //Desactivaciones y activaciones de botones
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
                button5.setEnabled(true);
                button6.setEnabled(true);
                button7.setEnabled(true);
                button8.setEnabled(true);
                button9.setEnabled(true);
                buttonComa.setEnabled(true);
                buttonAND.setVisibility(View.INVISIBLE);
                buttonOR.setVisibility(View.INVISIBLE);
                buttonXOR.setVisibility(View.INVISIBLE);
                buttonNOT.setVisibility(View.INVISIBLE);

                //Cambio de formato
                if (signo.equals("AND") || signo.equals("OR") || signo.equals("XOR")) {
                    n1 = Integer.parseInt(n1, 2) + "";
                    n2 = "";
                    signo = "";
                } else {
                    if (!n2.equals("")) {
                        n1 = Integer.parseInt(n1, 2) + "";
                        n2 = Integer.parseInt(n2, 2) + "";
                    } else if (!n1.equals("")) {
                        n1 = Integer.parseInt(n1, 2) + "";
                    }
                }
                grande.setText(n1 + signo + n2);
                peque.setText("0.0");
            }
        });
        binario.setOnClickListener(view -> {
            binario.setSelected(true);
            decimal.setSelected(false);
            if (!comprobante) {
                comprobante = true;
                //Desactivaciones y activaciones de botones
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                button5.setEnabled(false);
                button6.setEnabled(false);
                button7.setEnabled(false);
                button8.setEnabled(false);
                button9.setEnabled(false);
                buttonComa.setEnabled(false);
                buttonAND.setVisibility(View.VISIBLE);
                buttonOR.setVisibility(View.VISIBLE);
                buttonXOR.setVisibility(View.VISIBLE);
                buttonNOT.setVisibility(View.VISIBLE);

                //Cambio de formato
                if (!n2.equals("")) {
                    n1 = Integer.toBinaryString((int) (Double.parseDouble(n1)));
                    n2 = Integer.toBinaryString((int) (Double.parseDouble(n2)));
                } else if (!n1.equals("")) {
                    n1 = Integer.toBinaryString((int) (Double.parseDouble(n1)));
                }
                grande.setText(n1 + signo + n2);
                peque.setText("0.0");
            }
        });

        //Numeros
        button0.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 0;
                grande.setText(n1 + signo + "" + n2);
            } else {
                n1 = n1 + 0;
                grande.setText(n1);
            }
        });
        button1.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 1;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 1;
                grande.setText(n1);
            }
        });
        button2.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 2;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 2;
                grande.setText(n1);
            }
        });
        button3.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 3;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 3;
                grande.setText(n1);
            }
        });
        button4.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 4;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 4;
                grande.setText(n1);
            }
        });
        button5.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 5;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 5;
                grande.setText(n1);
            }
        });
        button6.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 6;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 6;
                grande.setText(n1);
            }
        });
        button7.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 7;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 7;
                grande.setText(n1);
            }
        });
        button8.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 8;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 8;
                grande.setText(n1);
            }
        });
        button9.setOnClickListener(view -> {
            if (!signo.equals("")) {
                n2 = n2 + 9;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 9;
                grande.setText(n1);
            }
        });

        //Coma
        buttonComa.setOnClickListener(view -> {
            String cadena = grande.getText().toString();
            if (!signo.equals("")) { // Si hay un signo
                if (cadena.charAt(cadena.length() - 1) == signo.charAt(0)) { //Si no hay numero despues del signo
                    n2 = "0.";
                    grande.setText(n1 + signo + n2);
                } else {
                    n2 = n2 + ".";
                    grande.setText(n1 + signo + n2);
                }
            } else if (cadena.equals("")) { //Si la cadena esta vacia y no hay signo
                n1 = "0.";
                grande.setText(n1);
            } else { //Si la cadena no esta vacia y no hay signo
                n1 = n1 + ".";
                grande.setText(n1);
            }
        });

        // Funciones de borrado
        buttonDel.setOnClickListener(view -> {
            String cadena = grande.getText().toString();
            if (!signo.equals("")) { // Si hay un signo
                if (cadena.charAt(cadena.length() - 1) == signo.charAt(0)) { //Si lo que borra es el signo
                    signo = "";
                    grande.setText(n1);
                } else {
                    cadena = cadena.substring(0, cadena.length() - 1);
                    n2 = cadena;
                    grande.setText(cadena);
                }
            } else if (!cadena.equals("")) { // Si la cadena no esta vacia y no hay signo
                cadena = cadena.substring(0, cadena.length() - 1);
                n1 = cadena;
                grande.setText(cadena);
            }
        });
        buttonC.setOnClickListener(view -> {
            n1 = "";
            n2 = "";
            signo = "";
            grande.setText("");
            peque.setText("0.0");
        });

        //Operaciones
        buttonSum.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "+";
                grande.setText(n1 + signo);
            }
        });
        buttonRes.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "-";
                grande.setText(n1 + signo);
            }
        });
        buttonMul.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "x";
                grande.setText(n1 + signo);
            }
        });
        buttonDiv.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "÷";
                grande.setText(n1 + signo);
            }
        });
        buttonPorc.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "%";
                grande.setText(n1 + signo);
            }
        });

        //Operaciones logicas
        buttonAND.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "AND";
                grande.setText(n1 + signo);
            }
        });
        buttonOR.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "OR";
                grande.setText(n1 + signo);
            }
        });
        buttonXOR.setOnClickListener(view -> {
            if (grande.getText() != "" && signo.equals("")) {
                signo = "XOR";
                grande.setText(n1 + signo);
            }
        });
        buttonNOT.setOnClickListener(view -> {
            if (grande.getText() != "" && !signo.equals("")) {
                StringBuilder rdo = new StringBuilder();
                for (int i = 0; i < n1.length(); i++) {
                    if (n1.charAt(i) == '0') {
                        rdo.append("1");
                    } else {
                        rdo.append("0");
                    }
                }
                grande.setText(rdo);
                peque.setText(n1 + "NOT");
                n1 = rdo + "";
            }
        });

        //El resultado
        buttonIgu.setOnClickListener(view -> {
            double primero, segundo;
            if (grande.getText() != "" && !signo.equals("")) {
                if (binario.isSelected()) {
                    primero = (double) Integer.parseInt(n1, 2);
                    segundo = (double) Integer.parseInt(n2, 2);
                } else {
                    primero = Double.parseDouble(n1);
                    segundo = Double.parseDouble(n2);
                }
                double resultado = 0;
                switch (signo) {
                    case "+":
                        resultado = primero + segundo;
                        break;
                    case "-":
                        resultado = primero - segundo;
                        break;
                    case "x":
                        resultado = primero * segundo;
                        break;
                    case "÷":
                        resultado = primero / segundo;
                        break;
                    case "%":
                        resultado = (primero / 100) * segundo;
                        break;
                    case "AND":
                        resultado = (int) (primero) & (int) (segundo);
                        break;
                    case "OR":
                        resultado = (int) (primero) | (int) (segundo);
                        break;
                    case "XOR":
                        resultado = (int) (primero) ^ (int) (segundo);
                        break;
                }
                if (binario.isSelected()) { //Muestra resultado en binario
                    String binn1 = Integer.toBinaryString(Integer.parseInt(n1, 2));
                    String binn2 = Integer.toBinaryString(Integer.parseInt(n2, 2));
                    String binrdo = Integer.toBinaryString((int) resultado);
                    grande.setText(binrdo);
                    peque.setText(binn1 + signo + binn2);
                    n1 = binrdo;
                } else { //Muestra resultado en decimal
                    grande.setText(resultado + "");
                    peque.setText(n1 + signo + n2);
                    n1 = resultado + "";
                }
                n2 = "";
                signo = "";
            }
        });
    }
}