package be.pxl.stefvrijens.pokebattle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.services.ImageService;


public class BattleVisuals extends Fragment {
    ImageView enemyImage;
    ImageView playerImage;
    TextView enemyName;
    TextView playerName;
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
        return view;
    }

    public void addToLog(String lineToAdd) {
        TextView tv = (TextView) getView().findViewById(R.id.battleLogLine);
        tv.setText(lineToAdd);
    }

    public void updateVisuals(Pokemon playerPokemon, Pokemon enemyPokemon) {
        ImageService is = new ImageService();
        enemyImage.setImageBitmap(is.getImageFromWeb(enemyPokemon.getSpecies().getImageUrl(), this.getContext()));
        playerImage.setImageBitmap(is.getImageFromWeb(playerPokemon.getSpecies().getImageUrl(), this.getContext()));
        enemyName.setText(enemyPokemon.getSpecies().getName());
        playerName.setText(playerPokemon.getSpecies().getName());
        enemyHealth.setMax(enemyPokemon.getSpecies().getHp() * 4);
        enemyHealth.setProgress(enemyPokemon.getCurrentHp());
        playerHealth.setMax(playerPokemon.getSpecies().getHp() * 4);
        playerHealth.setProgress(playerPokemon.getCurrentHp());
    }
}
