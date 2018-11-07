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

        String[] datos = new String[] {"","Casado", "Separado", "Viudo", "Otro"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spLista.setAdapter(adaptador);

        radGeneros.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radHombre) generoSeleccionado = "Hombre";
                if (checkedId == R.id.radMujer) generoSeleccionado = "Mujer";
            }

        });
        swHijos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (swHijos.isChecked() == true)
                {
                    seleccionado = "tiene hijos";
                }
                else if (swHijos.isChecked() == false)
                {
                    seleccionado = "no tiene hijos";
                }
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
                //txtResultado.setText("");

            }
        });
        btnGenerar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edtNombre.getText().toString().isEmpty()) {
                    edtNombre.setText("Falta el Nombre") ;
                    edtNombre.setTextColor(getColor(R.color.radMujer));
                    txtResultado.setText("Error al ingresar los datos");
                }
                if (edtApellidos.getText().toString().isEmpty())
                {
                    edtApellidos.setText("Falta los Apellidos");
                    edtApellidos.setTextColor(getColor(R.color.radMujer));
                    txtResultado.setText(edtNombre.getText().toString() + "," + edadResultado + "," + generoSeleccionado + " " + spLista.getSelectedItem().toString() + " y " + seleccionado);
                    txtResultado.setText("Error");

                }
                if (edtEdad.getText().toString().isEmpty())
                {
                    edtEdad.setText("Falta la edad");
                    edtEdad.setTextColor(getColor(R.color.radMujer));
                    txtResultado.setText(edtNombre.getText().toString() + "," + edtApellidos.getText().toString() + "," + generoSeleccionado + " " + spLista.getSelectedItem().toString() + " y " + seleccionado);
                    txtResultado.setText("Error");
                }
                else {


                    if (Integer.parseInt(edtEdad.getText().toString()) >= 18) {
                        edadResultado = "Mayor De Edad";
                    } else {
                        edadResultado = "Menor De Edad";
                    }


                    spLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            spLista.getSelectedItemPosition();
                            spLista.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    txtResultado.setText(edtApellidos.getText().toString() + " " + edtNombre.getText().toString() + "," + edadResultado + "," + generoSeleccionado + " " + spLista.getSelectedItem().toString() + " y " + seleccionado );
                }
            }
});
    }}
