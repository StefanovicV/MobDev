package be.pxl.stefvrijens.pokebattle.services;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Type;

/**
 * Created by 11500809 on 7/11/2017.
 */

public class AttackService {
    public static List<Attack> GetAttackObjectsList(Activity a) {

        RequestQueue requestQueue = Volley.newRequestQueue(a);
        final List<Attack> attacks = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2:8080/shop", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonAttackArray = response.getJSONArray("attacks");
                            for (int i = 1; i < jsonAttackArray.length(); i++){
                                JSONObject attackObj = jsonAttackArray.getJSONObject(i);

                                String name = attackObj.getString("attack");
                                Type type = Type.valueOf(attackObj.getString("type"));
                                int power = attackObj.getInt("power");
                                int accuracy = attackObj.getInt("accuracy");
                                int cost = attackObj.getInt("cost");

                                Attack attack = new Attack(name, type, power, accuracy, cost);
                                attacks.add(attack);
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
}
