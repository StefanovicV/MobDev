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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefv on 27-Oct-17.
 */

public class ShopService {

    public static List<String> GetItemList(Activity a) {

        RequestQueue requestQueue = Volley.newRequestQueue(a);
        final List<String> items = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://localhost:8080/shop", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItemArray = response.getJSONArray("items");
                            for (int i = 0; i < jsonItemArray.length(); i++){
                                JSONObject item = jsonItemArray.getJSONObject(i);

                                String name = item.getString("name");
                                int cost = item.getInt("cost");

                                items.add(name + " " + cost);
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

        return items;
    }

    public static List<String> GetAttackList(Activity a) {

        RequestQueue requestQueue = Volley.newRequestQueue(a);
        final List<String> attacks = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://localhost:8080/shop", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonAttackArray = response.getJSONArray("attacks");
                            for (int i = 1; i < jsonAttackArray.length(); i++){
                                JSONObject attack = jsonAttackArray.getJSONObject(i);

                                String name = attack.getString("name");
                                int cost = attack.getInt("cost");

                                attacks.add(name + " " + cost);
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

        return attacks;
    }

    public static List<String> GetPokemonList(Activity a) {

        RequestQueue requestQueue = Volley.newRequestQueue(a);
        final List<String> pokemons = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://localhost:8080/shop", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonPokemonArray = response.getJSONArray("pokemonSpecies");
                            for (int i = 1; i < jsonPokemonArray.length(); i++){
                                JSONObject pokemon = jsonPokemonArray.getJSONObject(i);

                                String name = pokemon.getString("name");
                                int cost = pokemon.getInt("cost");

                                pokemons.add(name + " " + cost);
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
