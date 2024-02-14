package com.vedruna.retrofitpokemonapi.model;

import com.google.gson.annotations.SerializedName;

public class PokemonByIdResponse {
    @SerializedName("base_experience")
    private int baseExperience;
    private String name;
    private int id;

    public int getBaseExperience() {
        return baseExperience;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
