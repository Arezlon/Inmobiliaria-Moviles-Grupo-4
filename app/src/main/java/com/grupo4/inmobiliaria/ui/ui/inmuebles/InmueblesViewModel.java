package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InmueblesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InmueblesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Inmuebles");
    }

    public LiveData<String> getText() {
        return mText;
    }
}