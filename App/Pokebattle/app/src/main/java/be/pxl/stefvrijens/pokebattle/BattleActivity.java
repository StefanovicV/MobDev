package be.pxl.stefvrijens.pokebattle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;

public class BattleActivity extends AppCompatActivity implements BattleVisuals.OnFragmentInteractionListener, BattleChoice.OnFragmentInteractionListener, BattleAttacks.OnAttackButtonClick {
    Pokemon playerPokemon;
    Pokemon enemyPokemon;
    Pokemon[] playerTeam;
    Pokemon[] enemyTeam;
    int enemyPokemonNumber;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        // TODO: Get playerTeam from localStorage
        enemyTeam = new Pokemon[6];
        enemyPokemonNumber = 0;
        generateEnemyTeam();
        playerPokemon = playerTeam[0];
        enemyPokemon = enemyTeam[0];
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void goToAttackStyles(View view) {
        
    }

    public void generateEnemyTeam() {
        for (int i = 0; i < 6; i++) {
            // TODO: Get pokemon from api, put in enemyTeam[i]
        }
    }

    public void useAttack(Attack attack) {
        // TODO: Disable controls
        // TODO: wait 2 sec
        if (playerPokemon.getSpecies().getSpeed() < enemyPokemon.getSpecies().getSpeed()) {
            enemyMove();
            if (playerPokemon.getCurrentHp() > 0) {
                enemyPokemon.calculateDamage(attack, playerPokemon);
                // TODO: Display in log: playerPokemon used attack
                // TODO: Display playerPokemon animation
            } else {
                deadPokemon(true);
            }
            if (enemyPokemon.getCurrentHp() <= 0) {
                deadPokemon(false);
            }
        } else {
            enemyPokemon.calculateDamage(attack, playerPokemon);
            // TODO: Display in log: playerPokemon used attack
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
        // TODO: Disable controls
        // TODO: Display in log: Come back, playerPokemon.
        // TODO: playerPokemon leave animation
        playerPokemon = pokemon;
        // TODO: Display in log: Go! Pokemon!.
        // TODO: playerPokemon entry animation
        // TODO: Wait 2 sec
        enemyMove();
        // TODO: Enable controls
    }

    public void useItem(String typeOfItem) {
        if (typeOfItem.toLowerCase().equals("potion")) {
            playerPokemon.setCurrentHp(playerPokemon.getCurrentHp() + 20);
        } else if (typeOfItem.toLowerCase().equals("superpotion")) {
            playerPokemon.setCurrentHp(playerPokemon.getCurrentHp() + 50);
        }
        // TODO: Display in log: You used a potion/superpotion, playerPokemon healed 20/50HP.
        // TODO: Wait 2 sec
        enemyMove();
    }

    public void enemyMove() {
        Attack enemyChosenAttack = enemyPokemon.getAttacks()[rand.nextInt(4)];
        playerPokemon.calculateDamage(enemyChosenAttack, enemyPokemon);
        // TODO: Display in log: enemyPokemon used enemyChosenAttack
        // TODO: Display enemyPokemon animation
        // TODO: Wait 2 sec
    }

    public void deadPokemon(boolean isPlayer) {
        if (isPlayer) {
            playerPokemon.setCurrentHp(0);
            // TODO: Display playerPokemon dead animation
            // TODO: Allow player to choose new pokemon (with >0 health)
            // TODO: battleOver(false) if no pokemon left with >0 health
            // TODO: Display new playerPokemon animation
        } else {
            enemyPokemon.setCurrentHp(0);
            // TODO: Display enemyPokemon dead animation
            enemyPokemonNumber++;
            if (enemyPokemonNumber < 6) {
                enemyPokemon = enemyTeam[enemyPokemonNumber];
                // TODO: Display new enemyPokemon animation
            } else {
                battleOver(true);
            }
        }
    }

    public void battleOver(boolean playerWins) {
        // TODO: Navigate to PostBattle with playerWins as variable
    }


    @Override
    public void buttonClickHandler(View view, int attackNumber) {
        useAttack(playerPokemon.getAttacks()[attackNumber]);
    }
}
