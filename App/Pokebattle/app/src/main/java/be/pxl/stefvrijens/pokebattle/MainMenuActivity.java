package be.pxl.stefvrijens.pokebattle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.FileOutputStream;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.domainclasses.PokemonSpecies;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;
import be.pxl.stefvrijens.pokebattle.services.PokemonService;

public class MainMenuActivity extends AppCompatActivity {
    Button shopButton;
    Button fightButton;
    Button myTeamButton;
    Button logOutButton;
    Attack firstAttack;
    PokemonSpecies firstSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initializeButtons();

        if (!playerDataExists()) {
            generatePlayerData();
        }
    }

    private void generatePlayerData() {
        PokemonService ps = new PokemonService();

        System.out.println("Creating PlayerData");
        ps.GetSpeciesById(25, this, new MainMenuCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                handleSpeciesServiceResponse(result);
            }
        });
        ps.GetAttackByName("Tackle", this, new MainMenuCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                handleAttackServiceResponse(result);
            }
        });
    }

    private void handleSpeciesServiceResponse(JSONObject response) {
        Gson gson = new Gson();
        firstSpecies = gson.fromJson(response.toString(), PokemonSpecies.class);
        createNewPlayerData();
    }

    private void handleAttackServiceResponse(JSONObject response) {
        Gson gson = new Gson();
        firstAttack = gson.fromJson(response.toString(), Attack.class);
        createNewPlayerData();
    }

    private void createNewPlayerData() {
        if (firstAttack != null && firstSpecies != null) {
            Pokemon firstPokemon = new Pokemon(1, firstSpecies, new Attack[]{firstAttack});
            Player player = new Player(new Pokemon[]{firstPokemon}, 50, 0, 0, new Attack[]{firstAttack}, new Pokemon[]{firstPokemon});
            try {
                InternalStorage.writeObject(this, "PlayerData", player);
            } catch (Exception ex) {
                System.err.println("Error creating data: " + ex.getMessage());
            }
        }
    }

    private boolean playerDataExists() {
        String[] files = fileList();
        for (int i = 0; i < files.length; i++) {
            System.out.println("File " + i + ": " + files[i]);
            if (files[i].equals("PlayerData")) {
                System.out.println("PlayerData exists");
                return true;
            }
        }
        return false;
    }

    private void initializeButtons() {
        shopButton = (Button) findViewById(R.id.shopButton);
        fightButton = (Button) findViewById(R.id.fightButton);
        myTeamButton = (Button) findViewById(R.id.myTeamButton);
        logOutButton = (Button) findViewById(R.id.logOutButton);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainMenuActivity.this, Shop.class);
                startActivity(in);
            }
        });
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainMenuActivity.this, PreBattleActivity.class);
                startActivity(in);
            }
        });
        myTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainMenuActivity.this, TeamBuilder.class);
                startActivity(in);
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPlayerData();
            }
        });
    }

    private void resetPlayerData() {
        deleteFile("PlayerData");
        generatePlayerData();
    }

    public interface MainMenuCallback {
        void onSuccess(JSONObject result);
    }
}
