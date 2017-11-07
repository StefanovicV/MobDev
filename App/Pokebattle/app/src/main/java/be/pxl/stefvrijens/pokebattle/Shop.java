package be.pxl.stefvrijens.pokebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
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
    TextView coinsCounter;
    TextView inventoryView;
    List<String> items;
    List<Attack> attackObjects;
    List<Attack> defaultAttackObject;
    List<PokemonSpecies> pokemonObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        listView = (ExpandableListView)findViewById(R.id.Shop);
        listView.setClickable(true);
        listView.setOnChildClickListener(this);
        inventoryView = (TextView) findViewById(R.id.shopInventory);
        coinsCounter = (TextView) findViewById(R.id.shopCoinsCounter);

        try {
            playerData = (Player) InternalStorage.readObject(this, "PlayerData");
            dataBinding();
        } catch (Exception ex) {
            System.err.println("Error reading playerData: " + ex.getMessage());
        }

        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);
    }

    private void savePlayerData() {
        try {
            InternalStorage.writeObject(this, "PlayerData", playerData);
        } catch (Exception ex) {
            System.out.println("Error writing playerData: " + ex.getMessage());
        }
    }

    private void dataBinding() {
        coinsCounter.setText("Coins: " + playerData.getOwnedCoins());
        inventoryView.setText("Potions: " + playerData.getOwnedPotions() + "\t Super Potions: " + playerData.getOwnedSuperPotions());
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        ShopService shopService = new ShopService();
        final Gson gson = new Gson();

        listDataHeader.add("Items");
        items = ShopService.GetItemList(this);
        listHash.put(listDataHeader.get(0), items);


        shopService.GetSpecies(this, new ShopCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                System.out.println("Species received");
                PokemonSpecies[] pokemonSpecies = gson.fromJson(result.toString(), PokemonSpecies[].class);
                pokemonObjects = new ArrayList<PokemonSpecies>(Arrays.asList(pokemonSpecies));
                final List<String> speciesStrings = new ArrayList<String>();
                for (PokemonSpecies ps: pokemonObjects) {
                    speciesStrings.add(ps.getName() + "\t\t\t" + ps.getCost());
                }
                listHash.put(listDataHeader.get(1), speciesStrings);
            }
        });
        shopService.GetAttacks(this, new ShopCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                System.out.println("Attacks received");
                Attack[] atts = gson.fromJson(result.toString(), Attack[].class);
                attackObjects = new ArrayList<Attack>(Arrays.asList(atts));
                final List<String> attackStrings =  new ArrayList<String>();
                for (Attack a:
                        attackObjects) {
                    attackStrings.add(a.getName() + "\t\t\t" + a.getCost());
                }
                listHash.put(listDataHeader.get(2), attackStrings);
            }
        });

        listDataHeader.add("Pokémon");
        listDataHeader.add("Attacks");
    }

    @Override
    public boolean onChildClick(ExpandableListView listView, View v, int groupPosition, int childPosition, long id) {
        if(groupPosition == 0){ //itemList
            if(childPosition == 0){
                if(playerData.getOwnedCoins() >= 10){
                    Log.e("Coins", "already owned " + playerData.getOwnedCoins());
                    playerData.setOwnedPotions(playerData.getOwnedPotions() + 1);
                    playerData.setOwnedCoins(playerData.getOwnedCoins() - 10);
                    Log.e("Coins", "already owned " + playerData.getOwnedCoins());
                    //listDataHeader.set(0, "Coins: " + reducedCoins);
                    dataBinding();
                    savePlayerData();
                    showToast("Bought a potion.");
                } else {
                    Log.e("Coins", "You have not enough coins left to buy that item!");
                    showToast("You don't have enough coins to buy that item!");
                }
            } else {
                if(playerData.getOwnedCoins() >= 20){
                    Log.e("Coins", "already owned " + playerData.getOwnedCoins());
                    playerData.setOwnedSuperPotions(playerData.getOwnedSuperPotions() + 1);
                    playerData.setOwnedCoins(playerData.getOwnedCoins() - 20);
                    Log.e("Coins", "already owned " + playerData.getOwnedCoins());
                    dataBinding();
                    savePlayerData();
                    showToast("Bought a super potion.");
                }else {
                    Log.e("Coins", "You have not enough coins left to buy that item!");
                    showToast("You don't have enough coins to buy that item!");
                }
            }
        } else {
            if(groupPosition == 1){ //pokemonList
                PokemonSpecies selectedPokemonSpecies = pokemonObjects.get(childPosition);
                boolean alreadyOwned = false;
                for (int i = 0; i < playerData.getOwnedPokemon().length; i++) {
                    if (playerData.getOwnedPokemon()[i].getSpecies().getId() == selectedPokemonSpecies.getId()) {
                        alreadyOwned = true;
                    }
                }
                if (playerData.getOwnedCoins() >= selectedPokemonSpecies.getCost()) {
                    if (!alreadyOwned) {
                        playerData.addPokemon(selectedPokemonSpecies);
                        playerData.setOwnedCoins(playerData.getOwnedCoins() - selectedPokemonSpecies.getCost());
                        dataBinding();
                        savePlayerData();
                        showToast("Bought a " + selectedPokemonSpecies.getName() + ".");
                    } else {
                        showToast("You already have a " + selectedPokemonSpecies.getName() + ".");
                    }
                } else {
                    showToast("You don't have enough coins to buy that pokemon!");
                }

            } else {
                if(groupPosition == 2){ //attackList
                    Attack selectedAttack = attackObjects.get(childPosition);
                    boolean alreadyOwned = false;
                    for (int i = 0; i < playerData.getOwnedAttacks().length; i++) {
                        if (playerData.getOwnedAttacks()[i].getName().equals(selectedAttack.getName())) {
                            alreadyOwned = true;
                        }
                    }
                    if (playerData.getOwnedCoins() >= selectedAttack.getCost()) {
                        if (!alreadyOwned) {
                            playerData.addAttack(selectedAttack);
                            playerData.setOwnedCoins(playerData.getOwnedCoins() - selectedAttack.getCost());
                            dataBinding();
                            savePlayerData();
                            showToast("Bought " + selectedAttack.getName() + ".");
                        } else {
                            showToast("You already have " + selectedAttack.getName() + ".");
                        }
                    } else {
                        showToast("You don't have enough coins to buy that attack!");
                    }
                } else {
                    Log.e("OnChildClick", "Something went wrong here");
                }
            }
        }
        return false;
    }

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public interface ShopCallback {
        void onSuccess(JSONArray result);
    }
}
