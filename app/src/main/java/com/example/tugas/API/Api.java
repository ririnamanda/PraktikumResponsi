package com.example.tugas.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String BASE_URL ="https://corona.lmao.ninja/";//URL utama API
    private static Retrofit retrofit;
    private static Api api;

    //fungsi untuk menerjemahkan data dari API
    private Api(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized Api getInstance(){
        if (api==null){
            api = new Api();
        }
        return api;
    }

    //fungsi untuk mengeksekusi API
    public static Interface getApi() {
        return retrofit.create(Interface.class);
    }
}
