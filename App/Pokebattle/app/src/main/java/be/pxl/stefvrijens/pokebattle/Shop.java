package be.pxl.stefvrijens.pokebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Item;
import be.pxl.stefvrijens.pokebattle.domainclasses.Type;

public class Shop extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

//        listView = (ExpandableListView)findViewById(R.id.Shop);
//        requestQueue = Volley.newRequestQueue(this);
//        initData();
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
//        listView.setAdapter(listAdapter);

        // TODO:Get current inventory/coins from localstorage
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Items");
        listDataHeader.add("Pokémon");
        listDataHeader.add("Attacks");

        final List<String> items = new ArrayList<>();
        final List<String> pokemons = new ArrayList<>();
        final List<String> attacks = new ArrayList<>();

        // TODO:GetAll from API

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
                            JSONArray jsonAttackArray = response.getJSONArray("attacks");
                            for (int i = 1; i < jsonAttackArray.length(); i++){
                                JSONObject attack = jsonAttackArray.getJSONObject(i);

                                String name = attack.getString("name");
                                int cost = attack.getInt("cost");

                                attacks.add(name + " " + cost);
                            }
                            JSONArray jsonPokemonArray = response.getJSONArray("attacks");
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

        listHash.put(listDataHeader.get(0), items);
        listHash.put(listDataHeader.get(1), pokemons);
        listHash.put(listDataHeader.get(2), attacks);
    }
}
