package com.grupo4.inmobiliaria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private EditText etMail, etClave;
    private Button btIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication())
                .create(MainActivityViewModel.class);
        inicializarVista();

        viewModel.getMensajeMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Error")
                        .setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        viewModel.getResultadoMutable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean ok) {
                if (ok){
                    startActivity(new Intent(getApplicationContext(), MenuNavegacion.class));
                }
            }
        });
    }

    private void inicializarVista(){
        etMail = findViewById(R.id.etMail);
        etClave = findViewById(R.id.etClave);
        btIngresar = findViewById(R.id.btIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                viewModel.verificarDatos(
                        etMail.getText().toString(),
                        etClave.getText().toString()
                );
            }
        });
    }
}