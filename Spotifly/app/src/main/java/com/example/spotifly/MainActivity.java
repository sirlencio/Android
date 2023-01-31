package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    public  EditText txt_nombre, txt_fecha;
    public RadioButton rbHombre, rbMujer;
    public ImageView imagen;

    public String sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nombre = (EditText) findViewById(R.id.editTextTextNombre);
        txt_fecha = (EditText) findViewById(R.id.editTextTextFecha);
        rbHombre = (RadioButton) findViewById(R.id.radioButtonHombre);
        rbMujer = (RadioButton) findViewById(R.id.radioButtonMujer);
        imagen = (ImageView) findViewById(R.id.imageView);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnClickListener(View -> selectSexo());
    }

    public void selectSexo() {
        if (rbMujer.isChecked()){
            sexo = "Mujer";
            imagen.setImageResource(R.drawable.women);
            Toast.makeText(this, "women", Toast.LENGTH_SHORT).show();
        }else{
            sexo = "Hombre";
            imagen.setImageResource(R.drawable.men);
            Toast.makeText(this, "men", Toast.LENGTH_SHORT).show();
        }
    }
    public void showDatePickerDialog(View view) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                txt_fecha.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void registrar(View view) {
    }

}
