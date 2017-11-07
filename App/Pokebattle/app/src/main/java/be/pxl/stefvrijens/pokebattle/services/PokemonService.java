package be.pxl.stefvrijens.pokebattle.services;

import android.app.Activity;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.internal.Excluder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import be.pxl.stefvrijens.pokebattle.MainMenuActivity;
import be.pxl.stefvrijens.pokebattle.PreBattleActivity;
import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.domainclasses.PokemonSpecies;
import be.pxl.stefvrijens.pokebattle.domainclasses.Type;

/**
 * Created by stefv on 07-Nov-17.
 */

public class PokemonService {
    private Pokemon resultPokemon;
    private PokemonSpecies resultSpecies;
    private Attack resultAttack;

    public void GetTeamByRating(int rating, Activity a, final PreBattleActivity.PreBattleCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(a);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://10.0.2.2:8080/pokemon?pokemonRating=" + rating, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                Pokemon[] team = gson.fromJson(response.toString(), Pokemon[].class);
                callback.onSuccess(team);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("Pokemonservice error: " + error.getMessage());
            }
        });

        requestQueue.add(request);
    }

    public void GetSpeciesById(int id, Activity a, final MainMenuActivity.MainMenuCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(a);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8080/species/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callback.onSuccess(response);
                } catch (Exception ex) {
                    System.err.println("Json exception: " + ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("PokemonService error: " + error.getMessage());
            }
        });
        requestQueue.add(request);
    }
    public void GetAttackByName(String name, Activity a, final MainMenuActivity.MainMenuCallback callback) {
        final RequestQueue requestQueue = Volley.newRequestQueue(a);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8080/attacks/" + name, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("PokemonService error: " + error.getMessage());
            }
        });
        requestQueue.add(request);
    }
	
	public static List<PokemonSpecies> GetPokemonObjectsList(Activity a) {

        RequestQueue requestQueue = Volley.newRequestQueue(a);
        final List<PokemonSpecies> pokemons = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8080/shop", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonPokemonArray = response.getJSONArray("pokemonSpecies");
                            for (int i = 1; i < jsonPokemonArray.length(); i++){
                                JSONObject pokemonObj = jsonPokemonArray.getJSONObject(i);

                                int id = pokemonObj.getInt("id");
                                String name = pokemonObj.getString("name");
                                Type type1 = Type.valueOf(pokemonObj.getString("type1"));
                                Type type2 = Type.valueOf(pokemonObj.getString("type2"));
                                int cost = pokemonObj.getInt("cost");
                                Gson gson = new Gson();
                                PokemonSpecies nextEvolution = gson.fromJson(pokemonObj.getString("nextEvolution"), PokemonSpecies.class);
                                int evolutionCost = pokemonObj.getInt("evolutionCost");
                                int hp = pokemonObj.getInt("hp");
                                int attack = pokemonObj.getInt("attack");
                                int defense = pokemonObj.getInt("defense");
                                int speed = pokemonObj.getInt("speed");
                                String imageUrl = pokemonObj.getString("imageUrl");

                                PokemonSpecies pokemonSpecies = new PokemonSpecies(id, name, type1, type2, cost, nextEvolution, evolutionCost, hp, attack, defense, speed, imageUrl);
                                pokemons.add(pokemonSpecies);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.getMessage());
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);

        return pokemons;
    }
}
