package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String n1 = "";
    String n2 = "";
    String signo = "";
    boolean operando;

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
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
            button5.setEnabled(true);
            button6.setEnabled(true);
            button7.setEnabled(true);
            button8.setEnabled(true);
            button9.setEnabled(true);
            buttonComa.setEnabled(true);
            buttonAND.setEnabled(false);
            buttonOR.setEnabled(false);
            buttonXOR.setEnabled(false);
            buttonNOT.setEnabled(false);
            if (n1 != ""){
                n1 = conversorBinarioDecimal(n1) + "";
            }else if(n2 != ""){
                n2 = conversorBinarioDecimal(n2) + "";
            }
            grande.setText(n1 + signo + n2);
            peque.setText("0.0");

            decimal.setSelected(true);
            binario.setSelected(false);

        });
        binario.setOnClickListener(view -> {
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
            button9.setEnabled(false);
            buttonComa.setEnabled(false);
            buttonAND.setEnabled(true);
            buttonOR.setEnabled(true);
            buttonXOR.setEnabled(true);
            buttonNOT.setEnabled(true);
            if (n1 != ""){
                n1 = conversorDecimalBinario(Long.parseLong(n1));
            }else if(n2 != ""){
                n2 = conversorDecimalBinario(Long.parseLong(n2));
            }
            grande.setText(n1 + signo + n2);
            peque.setText("0.0");
            binario.setSelected(true);
            decimal.setSelected(false);
        });

        //Numeros
        button0.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 0;
                grande.setText(n1 + signo + "" + n2);
            } else {
                n1 = n1 + 0;
                grande.setText(n1);
            }
        });
        button1.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 1;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 1;
                grande.setText(n1);
            }
        });
        button2.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 2;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 2;
                grande.setText(n1);
            }
        });
        button3.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 3;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 3;
                grande.setText(n1);
            }
        });
        button4.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 4;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 4;
                grande.setText(n1);
            }
        });
        button5.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 5;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 5;
                grande.setText(n1);
            }
        });
        button6.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 6;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 6;
                grande.setText(n1);
            }
        });
        button7.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 7;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 7;
                grande.setText(n1);
            }
        });
        button8.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 8;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 8;
                grande.setText(n1);
            }
        });
        button9.setOnClickListener(view -> {
            if (operando) {
                n2 = n2 + 9;
                grande.setText(n1 + signo + n2);
            } else {
                n1 = n1 + 9;
                grande.setText(n1);
            }
        });

        //La coma
        buttonComa.setOnClickListener(view -> {
            String cadena = (String) grande.getText();
            if (operando) {
                if (cadena.charAt(cadena.length() - 1) == signo.charAt(0)) {
                    n2 = "0.";
                    grande.setText(n1 + signo + n2);
                } else {
                    n2 = n2 + ".";
                    grande.setText(n1 + signo + n2);
                }
            } else if (cadena.equals("")) {
                n1 = "0.";
                grande.setText(n1);
            } else {
                n1 = n1 + ".";
                grande.setText(n1);
            }
        });

        // Funciones de borrado
        buttonDel.setOnClickListener(view -> {
            String cadena = (String) grande.getText();
            if (operando) {
                if (cadena.charAt(cadena.length() - 1) == signo.charAt(0)) {
                    signo = "";
                    operando = false;
                    grande.setText(n1);
                } else {
                    cadena = cadena.substring(0, cadena.length());
                    n2 = cadena;
                    grande.setText(cadena);
                }
            } else if (!cadena.equals("")) {
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
            if (grande.getText() != "") {
                if (!operando) {
                    signo = "+";
                    operando = true;
                    grande.setText(n1 + signo);
                }
            }
        });
        buttonRes.setOnClickListener(view -> {
            if (grande.getText() != "") {
                if (!operando) {
                    signo = "-";
                    operando = true;
                    grande.setText(n1 + signo);
                }
            }
        });
        buttonMul.setOnClickListener(view -> {
            if (grande.getText() != "") {
                if (!operando) {
                    signo = "x";
                    operando = true;
                    grande.setText(n1 + signo);
                }
            }
        });
        buttonDiv.setOnClickListener(view -> {
            if (grande.getText() != "") {
                if (!operando) {
                    signo = "÷";
                    operando = true;
                    grande.setText(n1 + signo);
                }
            }
        });
        buttonPorc.setOnClickListener(view -> {
            if (grande.getText() != "") {
                if (!operando) {
                    signo = "%";
                    operando = true;
                    grande.setText(n1 + signo);
                }
            }
        });

        //El resultado
        buttonIgu.setOnClickListener(view -> {
            if (grande.getText() != "" && operando) {
                if (binario.isSelected()) {
                    long primero = conversorBinarioDecimal(n1);
                    long segundo = conversorBinarioDecimal(n2);
                    long rdo;
                    String binario;
                    switch (signo) {
                        case "+":
                            rdo = primero + segundo;
                            binario = conversorDecimalBinario(rdo);
                            peque.setText(grande.getText());
                            grande.setText(binario);
                            n1 = binario;
                            n2 = "";
                            operando = false;
                            break;
                        case "-":
                            rdo = primero - segundo;
                            binario = conversorDecimalBinario(rdo);
                            peque.setText(grande.getText());
                            grande.setText(binario);
                            n1 = binario;
                            n2 = "";
                            operando = false;
                            break;
                        case "x":
                            rdo = primero * segundo;
                            binario = conversorDecimalBinario(rdo);
                            peque.setText(grande.getText());
                            grande.setText(binario);
                            n1 = binario;
                            n2 = "";
                            operando = false;
                            break;
                        case "÷":
                            rdo = primero / segundo;
                            binario = conversorDecimalBinario(rdo);
                            peque.setText(grande.getText());
                            grande.setText(binario);
                            n1 = binario;
                            n2 = "";
                            operando = false;
                            break;
                        case "%":
                            rdo = (primero / 100) * segundo;
                            binario = conversorDecimalBinario(rdo);
                            peque.setText(grande.getText());
                            grande.setText(binario);
                            n1 = binario;
                            n2 = "";
                            operando = false;
                            break;
                    }
                    signo = "";
                } else if (decimal.isSelected()){
                    double primero = Double.parseDouble(n1);
                    double segundo = Double.parseDouble(n2);
                    double resultado;
                    switch (signo) {
                        case "+":
                            resultado = primero + segundo;
                            peque.setText(grande.getText());
                            grande.setText(resultado + "");
                            n1 = resultado + "";
                            n2 = "";
                            operando = false;
                            break;
                        case "-":
                            resultado = primero - segundo;
                            peque.setText(grande.getText());
                            grande.setText(resultado + "");
                            n1 = resultado + "";
                            n2 = "";
                            operando = false;
                            break;
                        case "x":
                            resultado = primero * segundo;
                            peque.setText(grande.getText());
                            grande.setText(resultado + "");
                            n1 = resultado + "";
                            n2 = "";
                            operando = false;
                            break;
                        case "÷":
                            resultado = primero / segundo;
                            peque.setText(grande.getText());
                            grande.setText(resultado + "");
                            n1 = resultado + "";
                            n2 = "";
                            operando = false;
                            break;
                        case "%":
                            resultado = (primero / 100) * segundo;
                            peque.setText(grande.getText());
                            grande.setText(resultado + "");
                            n1 = resultado + "";
                            n2 = "";
                            operando = false;
                            break;
                    }
                    signo = "";
                }
            }
        });
    }

    public static long conversorBinarioDecimal(String binario) {
        // A este número le vamos a sumar cada valor binario
        long decimal = 0;
        int posicion = 0;
        // Recorrer la cadena...
        for (int x = binario.length() - 1; x >= 0; x--) {
            // Saber si es 1 o 0; primero asumimos que es 1 y abajo comprobamos
            short digito = 1;
            if (binario.charAt(x) == '0') {
                digito = 0;
            }
      /*
          Se multiplica el dígito por 2 elevado a la potencia
          según la posición; comenzando en 0, luego 1 y así
          sucesivamente
       */
            double multiplicador = Math.pow(2, posicion);
            decimal += digito * multiplicador;
            posicion++;
        }
        return decimal;
    }

    public static String conversorDecimalBinario(long decimal) {
        int numero = (int) decimal;
        return Integer.toBinaryString(numero);
    }
}