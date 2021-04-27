package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class InmuebleViewModel extends ViewModel {

    public MutableLiveData<Inmueble> inmuebleMutable;

    public LiveData<Inmueble> getInmuebleMutable(){
        if (inmuebleMutable == null){
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public void LeerInmueble(Bundle bundle){
        Inmueble inmueble = (Inmueble)bundle.getSerializable("inmueble");
        inmuebleMutable.setValue(inmueble);
    }

    public void CambioEstado(Inmueble inmueble){
        inmueble.setEstado(!inmueble.isEstado());
        ApiClient.getApi().actualizarInmueble(inmueble);
        inmuebleMutable.setValue(inmueble);
    }
}
