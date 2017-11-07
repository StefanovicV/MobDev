package be.pxl.stefvrijens.pokebattle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;

public class BattleActivity extends AppCompatActivity implements BattleAttacks.OnAttackButtonClick, BattleChoice.OnFightButtonClick,
        BattleChoice.OnUseItemButtonClick, BattleChoice.OnSwitchPokemonButtonClick {
    Pokemon playerPokemon;
    Pokemon enemyPokemon;
    Pokemon[] playerTeam;
    Pokemon[] enemyTeam;
    Player playerData;
    int enemyPokemonNumber;
    Random rand = new Random();
    FragmentManager manager;
    BattleVisuals visuals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        try {
            playerData = (Player) InternalStorage.readObject(this, "PlayerData");

        } catch (Exception ex) {
            System.err.println("Internalstorage error: " + ex.getMessage());
        }

        enemyTeam = (Pokemon[]) getIntent().getSerializableExtra("EnemyTeam");
        playerTeam = playerData.getTeam();
        enemyPokemonNumber = 0;
        playerPokemon = playerTeam[0];
        enemyPokemon = enemyTeam[0];
        if (enemyPokemon == null) {
            enemyPokemon = Pokemon.generateTestPokemon();
        }
        manager = getFragmentManager();
        visuals = (BattleVisuals) getSupportFragmentManager().findFragmentById(R.id.battleVisuals);
        visuals.updateVisuals(playerPokemon, enemyPokemon);
    }

    @Override
    public void doFightButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleAttacks(), "battleAttacks");
        transaction.commit();
        manager.executePendingTransactions();
        BattleAttacks bat = (BattleAttacks) manager.findFragmentByTag("battleAttacks");
        bat.updateButtonDatabinding(playerPokemon);
    }

    @Override
    public void doUseItemButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleItems());
        transaction.commit();
    }

    @Override
    public void doSwitchButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleSwitchPokemon());
        transaction.commit();
    }

    public void useAttack(Attack attack) {
        // TODO: Disable controls
        // TODO: wait 2 sec
        if (playerPokemon.getSpecies().getSpeed() < enemyPokemon.getSpecies().getSpeed()) {
            enemyMove();
            if (playerPokemon.getCurrentHp() > 0) {
                enemyPokemon.calculateDamage(attack, playerPokemon);
                visuals.addToLog(playerPokemon.getSpecies().getName() + " used " + attack.getName());
                visuals.updateVisuals(playerPokemon, enemyPokemon);
                // TODO: Display playerPokemon animation
            } else {
                deadPokemon(true);
            }
            if (enemyPokemon.getCurrentHp() <= 0) {
                deadPokemon(false);
            }
        } else {
            enemyPokemon.calculateDamage(attack, playerPokemon);
            visuals.addToLog(playerPokemon.getSpecies().getName() + " used " + attack.getName());
            visuals.updateVisuals(playerPokemon, enemyPokemon);
            // TODO: Display playerPokemon animation
            // TODO: Wait 2 sec

            if (enemyPokemon.getCurrentHp() > 0) {
                enemyMove();
            } else {
                deadPokemon(false);
            }
            if (playerPokemon.getCurrentHp() <= 0) {
                deadPokemon(true);
            }
        }
        // TODO: wait 2 sec
        // TODO: Enable controls
    }

    public void switchPokemon(Pokemon pokemon) {
        if (pokemon.getCurrentHp() > 0) {
            // TODO: Disable controls
            visuals.addToLog("Come back, " + playerPokemon.getSpecies().getName());
            // TODO: playerPokemon leave animation
            playerPokemon = pokemon;
            visuals.addToLog("Go, " + pokemon.getSpecies().getName() + "!");
            visuals.updateVisuals(playerPokemon, enemyPokemon);
            // TODO: playerPokemon entry animation
            // TODO: Wait 2 sec
            enemyMove();
            // TODO: Enable controls
        } else {
            visuals.addToLog("This pokemon can't fight anymore");
        }
    }

    public void useItem(String typeOfItem) {
        if (typeOfItem.toLowerCase().equals("potion")) {
            playerPokemon.setCurrentHp(playerPokemon.getCurrentHp() + 20);
            playerData.setOwnedPotions(playerData.getOwnedPotions() - 1);
            visuals.addToLog("You used a potion " + playerPokemon.getSpecies().getName() + " healed 20HP.");
            visuals.updateVisuals(playerPokemon, enemyPokemon);
        } else if (typeOfItem.toLowerCase().equals("superpotion")) {
            playerPokemon.setCurrentHp(playerPokemon.getCurrentHp() + 50);
            playerData.setOwnedSuperPotions(playerData.getOwnedSuperPotions() - 1);
            visuals.addToLog("You used a Super Potion " + playerPokemon.getSpecies().getName() + " healed 50HP.");
            visuals.updateVisuals(playerPokemon, enemyPokemon);
        }
        try {
            InternalStorage.writeObject(this, "PlayerData", playerData);
        } catch (Exception ex) {
            System.err.println("Error writing playerdata: " + ex.getMessage());
        }
        // TODO: Wait 2 sec
        enemyMove();
    }

    public void enemyMove() {
        Attack enemyChosenAttack = enemyPokemon.getAttacks()[rand.nextInt(enemyPokemon.getAttacks().length)];
        playerPokemon.calculateDamage(enemyChosenAttack, enemyPokemon);
        visuals.addToLog("The enemy " + enemyPokemon.getSpecies().getName() + " used " + enemyChosenAttack.getName());
        visuals.updateVisuals(playerPokemon, enemyPokemon);
        // TODO: Display enemyPokemon animation
        // TODO: Wait 2 sec
    }

    public void deadPokemon(boolean isPlayer) {
        if (isPlayer) {
            visuals.addToLog(playerPokemon.getSpecies().getName() + " fainted.");
            playerPokemon.setCurrentHp(0);
            boolean hasSurvivingPokemon = false;
            for (int i = 0; i < playerTeam.length; i++) {
                if (playerTeam[i].getCurrentHp() > 0) {
                    switchPokemon(playerTeam[i]);
                    hasSurvivingPokemon = true;
                    break;
                }
            }
            // TODO: Display playerPokemon dead animation
            // BattleOver(false) if no pokemon left with >0 health
            if (hasSurvivingPokemon == false) {
                visuals.addToLog("You lost.");
                battleOver(false);
            }
            // TODO: Display new playerPokemon animation
        } else {
            visuals.addToLog("Enemy " + enemyPokemon.getSpecies().getName() + " fainted.");
            enemyPokemon.setCurrentHp(0);
            // TODO: Display enemyPokemon dead animation
            enemyPokemonNumber++;
            if (enemyPokemonNumber < enemyTeam.length) {
                enemyPokemon = enemyTeam[enemyPokemonNumber];
                visuals.updateVisuals(playerPokemon, enemyPokemon);
                // TODO: Display new enemyPokemon animation
            } else {
                battleOver(true);
            }
        }
    }

    public void battleOver(boolean playerWins) {
        Intent in = new Intent(BattleActivity.this, PostBattle.class);
        in.putExtra("PlayerWon", playerWins);
        in.putExtra("EnemyTeam", enemyTeam);
        startActivity(in);
    }


    @Override
    public void buttonClickHandler(View view, int attackNumber) {
        useAttack(playerPokemon.getAttacks()[attackNumber]);
    }
}
