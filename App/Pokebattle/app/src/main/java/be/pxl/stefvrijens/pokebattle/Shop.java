package be.pxl.stefvrijens.pokebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.domainclasses.PokemonSpecies;
import be.pxl.stefvrijens.pokebattle.services.AttackService;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;
import be.pxl.stefvrijens.pokebattle.services.PokemonService;
import be.pxl.stefvrijens.pokebattle.services.ShopService;

public class Shop extends AppCompatActivity implements ExpandableListView.OnChildClickListener{
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private ShopService shopService;
    Player playerData;
    int alreadyOwnedPotions;
    int alreadyOwnedSuperPotions;
    int alreadyOwnedCoins;
    List<String> items;
    List<String> pokemons;
    List<String> attacks;
    List<String> coins;
    List<Attack> attackObjects;
    List<Attack> defaultAttackObject;
    List<Pokemon> pokemonObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        try {
            playerData = (Player) InternalStorage.readObject(this, "PlayerData");
            alreadyOwnedPotions = playerData.getOwnedPotions();
            alreadyOwnedSuperPotions = playerData.getOwnedPotions();
            alreadyOwnedCoins = playerData.getOwnedCoins();
        } catch (Exception ex) {
            System.err.println("Error reading playerData: " + ex.getMessage());
        }

        listView = (ExpandableListView)findViewById(R.id.Shop);
        listView.setClickable(true);
        listView.setOnChildClickListener(this);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        // TODO:Get current inventory/coins from localstorage
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Coins: " + alreadyOwnedCoins);
        listDataHeader.add("Items");
        listDataHeader.add("Pokémon");
        listDataHeader.add("Attacks");

        coins = new ArrayList<>();
        coins.add("You currently have " + alreadyOwnedCoins + " coins!");
        items = ShopService.GetItemList(this);
        pokemons = ShopService.GetPokemonList(this);
        attacks = ShopService.GetAttackList(this);

        listHash.put(listDataHeader.get(0), coins);
        listHash.put(listDataHeader.get(1), items);
        listHash.put(listDataHeader.get(2), pokemons);
        listHash.put(listDataHeader.get(3), attacks);
    }

    @Override
    public boolean onChildClick(ExpandableListView listView, View v, int groupPosition, int childPosition, long id) {
        if(groupPosition == 1){ //itemList
            if(childPosition == 0){
                if(alreadyOwnedCoins >= 10){
                    Log.e("Coins", "already owned " + alreadyOwnedCoins);
                    playerData.setOwnedPotions(alreadyOwnedPotions++);
                    int reducedCoins = alreadyOwnedCoins - 10;
                    playerData.setOwnedCoins(reducedCoins);
                    alreadyOwnedCoins = reducedCoins;
                    Log.e("Coins", "already owned " + reducedCoins);
                    //listDataHeader.set(0, "Coins: " + reducedCoins);
                } else {
                    Log.e("Coins", "You have not enough coins left to buy that item!");
                }
            } else {
                if(alreadyOwnedCoins >= 20){
                    Log.e("Coins", "already owned " + alreadyOwnedCoins);
                    playerData.setOwnedSuperPotions(alreadyOwnedSuperPotions++);
                    int reducedCoins = alreadyOwnedCoins - 20;
                    playerData.setOwnedCoins(reducedCoins);
                    alreadyOwnedCoins = reducedCoins;
                    Log.e("Coins", "already owned " + reducedCoins);
                }else {
                    Log.e("Coins", "You have not enough coins left to buy that item!");
                }
            }
        } else {
            if(groupPosition == 2){ //pokemonList
                PokemonSpecies pokemonSpecies = PokemonService.GetPokemonObjectsList(this).get(childPosition);
                Log.e("Pokemon", ""+playerData.getOwnedPokemon());

                Attack defaultAtt = AttackService.GetAttackObjectsList(this).get(1);
                defaultAttackObject.add(defaultAtt);
                Attack[] defAtt = new Attack[defaultAttackObject.size()];

                Pokemon pokemon = new Pokemon(childPosition, pokemonSpecies, defaultAttackObject.toArray(defAtt));
                pokemonObjects.add(pokemon);
                Pokemon[] ps = new Pokemon[pokemonObjects.size()];

                playerData.setOwnedPokemon(pokemonObjects.toArray(ps));
                Log.e("Pokemon", ""+playerData.getOwnedPokemon());
            } else {
                if(groupPosition == 3){ //attackList
                    Attack attack = AttackService.GetAttackObjectsList(this).get(childPosition+1);
                    attackObjects.add(attack);
                    Attack[] as = new Attack[attackObjects.size()];
                    Log.e("Attacks", ""+playerData.getOwnedAttacks());
                    playerData.setOwnedAttacks(attackObjects.toArray(as));
                    Log.e("Attacks", ""+playerData.getOwnedAttacks());
                } else {
                    Log.e("OnChildClick", "Something went wrong here");
                }
            }
        }
        return false;
    }
}
