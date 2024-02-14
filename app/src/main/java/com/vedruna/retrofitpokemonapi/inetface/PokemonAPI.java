package com.vedruna.retrofitpokemonapi.inetface;

import com.vedruna.retrofitpokemonapi.model.PokemonByIdResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);
}