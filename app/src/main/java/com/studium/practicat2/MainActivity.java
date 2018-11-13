package com.studium.practicat2;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtApellidos, edtEdad;
    RadioGroup radGeneros;
    RadioButton radHombre, radMujer;
    Spinner spLista;
    Switch swHijos;
    Button btnGenerar;
    ImageButton imgValores;
    String[] datos;
    TextView txtResultado;
    String generoSeleccionado = "";
    String edadResultado;
    String seleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre  = findViewById(R.id.edtNombre);
        edtApellidos  = findViewById(R.id.edtApellidos);
        edtEdad = findViewById(R.id.edtEdad);
        radGeneros = findViewById(R.id.radGeneros);
        radHombre = findViewById(R.id.radHombre);
        radMujer  = findViewById(R.id.radMujer);
        spLista  =findViewById(R.id.spLista);
        swHijos = findViewById(R.id.swHijos);
        btnGenerar = findViewById(R.id.btnGenerar);
        imgValores = findViewById(R.id.imgValores);
        txtResultado = findViewById(R.id.txtResultado);

        String[] datos = new String[] {getResources().getString(R.string.Vacio),getResources().getString(R.string.Casado),getResources().getString(R.string.Separado),getResources().getString(R.string.Viudo),getResources().getString(R.string.Otro)};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spLista.setAdapter(adaptador);

        radGeneros.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radHombre) generoSeleccionado = getResources().getString(R.string.radHombre);
                if (checkedId == R.id.radMujer) generoSeleccionado = getResources().getString(R.string.radMujer);
            }
        });
        swHijos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (swHijos.isChecked() == true)
                {
                    seleccionado = getResources().getString(R.string.TieneHijos);
                }
                if (swHijos.isChecked() == false)
                {
                    seleccionado = getResources().getString(R.string.NoTieneHijos);
                }
            }
        });
        spLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spLista.getSelectedItemPosition();
                spLista.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        imgValores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNombre.setText("");
                edtApellidos.setText("");
                edtEdad.setText("");
                radHombre.setChecked(false);
                radMujer.setChecked(false);
                swHijos.setChecked(false);
                spLista.setSelection(0);
                txtResultado.setText("");
            }
        });
        btnGenerar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edtNombre.getText().toString().isEmpty()) {
                    txtResultado.setText(getResources().getString(R.string.FaltaNombre));
                    txtResultado.setTextColor(getColor(R.color.radMujer));
                }
                else{
                        if (edtApellidos.getText().toString().isEmpty()) {
                            txtResultado.setText(getResources().getString(R.string.FaltaApellidos));
                            txtResultado.setTextColor(getColor(R.color.radMujer));
                        } else {
                            if (edtEdad.getText().toString().isEmpty()) {
                                txtResultado.setText(getResources().getString(R.string.FaltaEdad));
                                txtResultado.setTextColor(getColor(R.color.radMujer));
                            } else {
                                if (Integer.parseInt(edtEdad.getText().toString()) >= 18) {
                                    edadResultado = getResources().getString(R.string.MayorEdad);
                                } else {
                                    edadResultado = getResources().getString(R.string.MenorEdad);
                                }
                                txtResultado.setText(edtApellidos.getText().toString() + " " + edtNombre.getText().toString() + "," + edadResultado + "," + generoSeleccionado + " " + spLista.getSelectedItem().toString() + " y " + seleccionado );
                                txtResultado.setTextColor(getColor(R.color.negro));
                            }
                        }

                }


            }
});
    }}
