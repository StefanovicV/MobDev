package be.pxl.stefvrijens.pokebattle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.domainclasses.PokemonSpecies;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;

public class MainMenuActivity extends AppCompatActivity {
    Button shopButton;
    Button fightButton;
    Button myTeamButton;
//    Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initializeButtons();

        if (!playerDataExists()) {
            try {
                System.out.println("Creating PlayerData");
                InternalStorage.writeObject(this, "PlayerData", Player.generateInitialPlayerData());
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

//            //TODO: Get Pikachu and tackle from API
//            PokemonSpecies firstSpecies = null;
//            Attack[] firstSpeciesAttack = new Attack[1];
//            Pokemon[] firstPokemon = new Pokemon[]{new Pokemon(1, firstSpecies, firstSpeciesAttack)};
//            Player player = new Player(firstPokemon, 50, 0, 0, firstSpeciesAttack, firstPokemon);
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
//        logOutButton = (Button) findViewById(R.id.logOutButton);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainMenuActivity.this, Shop.class);
                startActivity(in);
            }
        });
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainMenuActivity.this, PreBattleActivity.class);
                startActivity(in);
            }
        });
        myTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainMenuActivity.this, TeamBuilder.class);
                startActivity(in);
            }
        });
//        logOutButton.setOnClickListener(new View.OnClickListener() {
//
//        });
    }
}
