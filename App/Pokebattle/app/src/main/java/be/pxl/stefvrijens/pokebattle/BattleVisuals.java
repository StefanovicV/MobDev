package be.pxl.stefvrijens.pokebattle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.services.ImageService;
import be.pxl.stefvrijens.pokebattle.services.PokemonEntryAnimation;
import be.pxl.stefvrijens.pokebattle.services.ProgressBarAnimation;


public class BattleVisuals extends Fragment {
    ImageView enemyImage;
    ImageView playerImage;
    TextView enemyName;
    TextView playerName;
    TextView battleLog;
    ProgressBar enemyHealth;
    ProgressBar playerHealth;


    public BattleVisuals() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_battle_visuals, container, false);
        enemyImage = (ImageView) view.findViewById(R.id.enemyPokemonImage);
        playerImage = (ImageView) view.findViewById(R.id.playerPokemonImage);
        enemyName = (TextView) view.findViewById(R.id.enemyPokemonName);
        playerName = (TextView) view.findViewById(R.id.playerPokemonName);
        enemyHealth = (ProgressBar) view.findViewById(R.id.enemyPokemonHealth);
        playerHealth = (ProgressBar) view.findViewById(R.id.playerPokemonHealth);
        battleLog = (TextView) view.findViewById(R.id.battleLogLine);
        battleLog.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    public void addToLog(String lineToAdd) {
        battleLog.setText(battleLog.getText() + "\n" + lineToAdd);

    }

    public void updateVisuals(Pokemon playerPokemon, Pokemon enemyPokemon) {
        ImageService is = new ImageService();
        is.getImageFromWeb(enemyPokemon.getSpecies().getImageUrl(), this.getContext(), enemyImage);
        is.getImageFromWeb(playerPokemon.getSpecies().getImageUrl(), this.getContext(), playerImage);
        enemyName.setText(enemyPokemon.getSpecies().getName());
        playerName.setText(playerPokemon.getSpecies().getName());
        enemyHealth.setMax(enemyPokemon.getSpecies().getHp() * 4);
        playerHealth.setMax(playerPokemon.getSpecies().getHp() * 4);
        ProgressBarAnimation playerAnimation = new ProgressBarAnimation(playerHealth, playerHealth.getProgress(), playerPokemon.getCurrentHp());
        playerAnimation.setDuration(1000);
        playerHealth.startAnimation(playerAnimation);
        ProgressBarAnimation enemyAnimation = new ProgressBarAnimation(enemyHealth, enemyHealth.getProgress(), enemyPokemon.getCurrentHp());
        enemyAnimation.setDuration(1000);
        enemyHealth.startAnimation(enemyAnimation);
    }
    public void pokemonEntry(boolean isPlayer, boolean isLeaving) {
        PokemonEntryAnimation animation;
        int alpha;
        if (isLeaving) {
            alpha = 0;
        } else  {
            alpha = 255;
        }
        if (isPlayer) {
            animation = new PokemonEntryAnimation(playerImage, alpha);
        } else {
            animation = new PokemonEntryAnimation(enemyImage, alpha);
        }
        animation.setDuration(1000);
        if (isPlayer) {
            playerImage.startAnimation(animation);
        } else {
            enemyImage.startAnimation(animation);
        }
    }
}
