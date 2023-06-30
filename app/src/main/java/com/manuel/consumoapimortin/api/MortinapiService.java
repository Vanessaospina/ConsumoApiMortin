package com.manuel.consumoapimortin.api;

import com.manuel.consumoapimortin.MortinRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MortinapiService {
    @GET("character")
    Call<MortinRespuesta> obtenerListaMortin();
}
