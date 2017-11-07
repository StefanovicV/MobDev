package be.pxl.stefvrijens.pokebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.pxl.stefvrijens.pokebattle.services.ShopService;

public class Shop extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private ShopService shopService;

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

        listDataHeader.add("Items");
        listDataHeader.add("Pokémon");
        listDataHeader.add("Attacks");

        final List<String> items = ShopService.GetItemList(this);
        final List<String> pokemons = ShopService.GetPokemonList(this);
        final List<String> attacks = ShopService.GetAttackList(this);

        listHash.put(listDataHeader.get(0), items);
        listHash.put(listDataHeader.get(1), pokemons);
        listHash.put(listDataHeader.get(2), attacks);
    }
}
