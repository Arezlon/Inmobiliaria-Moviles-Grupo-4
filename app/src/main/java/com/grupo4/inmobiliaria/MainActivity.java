package com.grupo4.inmobiliaria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grupo4.inmobiliaria.ui.MenuNavegacion;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private EditText etMail, etClave;
    private Button btIngresar, btMostrarContraseña;

    private LoginAcelerometroListener listener;
    private SensorManager sensorManager;
    private Sensor acelerometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},2000);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
        btMostrarContraseña = findViewById(R.id.btMostrarContraseña);

        btIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                viewModel.verificarDatos(
                        etMail.getText().toString(),
                        etClave.getText().toString()
                );
            }
        });

        btMostrarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = etClave.getSelectionStart();
                if (etClave.getTransformationMethod() == PasswordTransformationMethod.getInstance()){
                    etClave.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    btMostrarContraseña.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.outline_visibility_off_24, 0, 0);
                } else {
                    etClave.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    btMostrarContraseña.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.outline_visibility_24, 0, 0);
                }
                etClave.setSelection(pos);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listener = new LoginAcelerometroListener();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (acelerometro != null){
            sensorManager.registerListener(listener, acelerometro, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null){
            sensorManager.unregisterListener(listener);
        }
    }

    private class LoginAcelerometroListener implements SensorEventListener{
        public long ultimoEvento = 0;
        public float ultimoValor = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {
            long tiempoActual = System.currentTimeMillis();

            if (tiempoActual - ultimoEvento > 200){
                ultimoEvento = tiempoActual;

                float valor = event.values[0];
                if (ultimoValor - valor > 10 || ultimoValor - valor < -10){
                    if (ultimoValor > 0 && valor > 0 || ultimoValor < 0 && valor < 0) {

                    } else {
                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 2664123456"));
                        startActivity(i);
                    }
                }
                ultimoValor = valor;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}