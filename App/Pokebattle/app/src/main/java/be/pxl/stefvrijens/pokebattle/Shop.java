package be.pxl.stefvrijens.pokebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        listView = (ExpandableListView)findViewById(R.id.Shop);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        // TODO:Get current inventory/coins from localstorage
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        // TODO:GetAll from API

        listDataHeader.add("Items");
        listDataHeader.add("Pokémon");
        listDataHeader.add("Attacks");

        List<String> items = new ArrayList<>();
        items.add("Potion");
        items.add("Super Potion");

        List<String> pokemon = new ArrayList<>();
        pokemon.add("Pidgey");
        pokemon.add("Rattata");
        pokemon.add("Pikachu");

        List<String> attacks = new ArrayList<>();
        attacks.add("Thunderbolt");
        attacks.add("Tackle");
        attacks.add("Bite");

        listHash.put(listDataHeader.get(0), items);
        listHash.put(listDataHeader.get(1), pokemon);
        listHash.put(listDataHeader.get(2), attacks);
    }
}
