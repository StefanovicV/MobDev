package be.pxl.stefvrijens.pokebattle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;

public class BattleActivity extends AppCompatActivity implements BattleVisuals.OnFragmentInteractionListener, BattleChoice.OnFragmentInteractionListener, BattleAttacks.OnAttackButtonClick, BattleItems.OnFragmentInteractionListener, BattleSwitchPokemon.OnFragmentInteractionListener,
        BattleChoice.OnFightButtonClick, BattleChoice.OnUseItemButtonClick, BattleChoice.OnSwitchPokemonButtonClick {
    Pokemon playerPokemon;
    Pokemon enemyPokemon;
    List<Pokemon> playerTeam;
    List<Pokemon> enemyTeam;
    int enemyPokemonNumber;
    Random rand = new Random();
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        // TODO: Get enemyTeam from previous activity
        // TODO: Get playerTeam from localStorage
        enemyTeam = new ArrayList<Pokemon>();
        enemyPokemonNumber = 0;
        playerPokemon = playerTeam.get(0);
        enemyPokemon = enemyTeam.get(0);
        manager = getFragmentManager();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void doFightButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleAttacks());
        transaction.commit();
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
        // TODO: Update LOCALSTORAGE (reduce potions/superpotions by 1)
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


            // BattleOver(false) if no pokemon left with >0 health
            boolean hasSurvivingPokemon = false;
            for (int i = 0; i < 6; i++) {
                if (playerTeam.get(i).getCurrentHp() > 0) {
                    hasSurvivingPokemon = true;
                }
            }
            if (hasSurvivingPokemon == false) {
                battleOver(false);
            }


            // TODO: Display new playerPokemon animation
        } else {
            enemyPokemon.setCurrentHp(0);
            // TODO: Display enemyPokemon dead animation
            enemyPokemonNumber++;
            if (enemyPokemonNumber < 6) {
                enemyPokemon = enemyTeam.get(enemyPokemonNumber);
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
