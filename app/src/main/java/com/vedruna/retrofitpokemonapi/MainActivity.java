package com.vedruna.retrofitpokemonapi;


import com.vedruna.retrofitpokemonapi.inetface.PokemonAPI;
import com.vedruna.retrofitpokemonapi.model.PokemonByIdResponse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String URL_BASE = "https://pokeapi.co/api/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextPokemonId = findViewById(R.id.editTextPokemonId);
        Button buttonSearch = findViewById(R.id.buttonSearch);
        TextView textViewPokemonName = findViewById(R.id.textViewPokemonName);
        TextView textViewPokemonId = findViewById(R.id.textViewPokemonId);
        TextView textViewBaseExperience = findViewById(R.id.textViewBaseExperience);

        /**
         * Configuración de Retrofit
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * Crear instancia de la interfaz
         */
        PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);

        buttonSearch.setOnClickListener(v -> {
            String pokemonId = editTextPokemonId.getText().toString();

            /**
             * Llamar al endpoint para obtener información de un Pokémon
             */
            Call<PokemonByIdResponse> call = pokemonAPI.getPokemonById(pokemonId);

            /**
             * Enqueue para llamada asíncrona
             */
            call.enqueue(new Callback<PokemonByIdResponse>() {
                @Override
                public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                    if (response.isSuccessful()) {
                        PokemonByIdResponse pokemon = response.body();
                        if (pokemon != null) {
                            // Mostrar los detalles del Pokémon en las vistas
                            textViewPokemonName.setText("Name: " + pokemon.getName());
                            textViewPokemonId.setText("ID: " + pokemon.getId());
                            textViewBaseExperience.setText("Base Experience: " + pokemon.getBaseExperience());
                        } else {
                            Log.e("Error", "No se pudo obtener el Pokémon");
                        }
                    } else {
                        Log.e("Error", "Hubo un error inesperado!");
                    }
                }

                /**
                 * Mostar error
                 * @param call
                 * @param t
                 */
                @Override
                public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                    Log.e("Error", "Fallo la llamada: " + t.getMessage());
                }
            });
        });
    }
}