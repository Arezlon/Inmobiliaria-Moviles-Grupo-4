package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;

public class ContratoViewModel extends ViewModel {

    public MutableLiveData<Contrato> contratoMutable;

    public LiveData<Contrato> getContratoMutable(){
        if (contratoMutable == null){
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void LeerContrato(Bundle bungle){
        Contrato contrato = (Contrato)bungle.getSerializable("contrato");
        contratoMutable.setValue(contrato);
    }
}
