package be.pxl.stefvrijens.pokebattle.services;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.pxl.stefvrijens.pokebattle.domainclasses.PokemonSpecies;

/**
 * Created by stefv on 27-Oct-17.
 */

public class ShopService {
    public static void GetSpecies() {
        String url = "http://localhost:8080/species";
        PokemonSpecies[] results;
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                    } catch (JSONException ex) {
                        System.err.println(ex);
                    }
                    System.out.println(object);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println(error);
            }
        });
    }

}
