package com.grupo4.inmobiliaria.ui.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.grupo4.inmobiliaria.modelo.Propietario;
import com.grupo4.inmobiliaria.request.ApiClient;

public class EditarPerfilViewModel extends ViewModel {
    public MutableLiveData<Propietario> propietarioMutable;
    public MutableLiveData<String> errorMutable;

    public LiveData<Propietario> getPropietarioMutable(){
        if (propietarioMutable == null){
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getErrorMutable(){
        if (errorMutable == null){
            errorMutable = new MutableLiveData<>();
        }
        return errorMutable;
    }

    public void ObtenerPropietario(){
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();

        propietarioMutable.setValue(p);
    }

    public void ModificarPropietario(Propietario p){
        if(p.getNombre().isEmpty() || p.getApellido().isEmpty() || p.getDni() == -1 || p.getEmail().isEmpty() || p.getTelefono().isEmpty()){
            errorMutable.setValue("Error, los campos no pueden estar vacios.");
        }else{
            ApiClient api = ApiClient.getApi();
            api.actualizarPerfil(p);
            errorMutable.setValue("EXITO");
        }

    }
}