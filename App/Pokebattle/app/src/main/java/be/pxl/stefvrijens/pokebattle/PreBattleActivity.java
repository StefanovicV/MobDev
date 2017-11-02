package be.pxl.stefvrijens.pokebattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;

public class PreBattleActivity extends AppCompatActivity {
    Button fightButton;
    Button teamButton;

    ToggleButton easyToggleButton;
    ToggleButton mediumToggleButton;
    ToggleButton hardToggleButton;

    Pokemon[] playerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_battle);
        initializeButtons();
        // TODO: Get Player.Team from LOCALSTORAGE
    }

    private Pokemon[] generateEnemyTeam() {
        int playerTeamRating = 0;
        int currentEnemyTeamRating = 0;
        int enemyTeamRatingGuide;
        Pokemon[] team = new Pokemon[6];

        for (int i = 0; i < playerTeam.length; i++) {
            playerTeamRating += playerTeam[i].getPokemonRating();
        }

        if (easyToggleButton.isChecked()) {
            enemyTeamRatingGuide = playerTeamRating - 200;
        } else if (mediumToggleButton.isChecked()) {
            enemyTeamRatingGuide = playerTeamRating;
        } else {
            enemyTeamRatingGuide = playerTeamRating + 200;
        }

        for (int i = 0; i < 6; i++) {
            Pokemon pokemon;
            int thisPokemonRatingGuide = enemyTeamRatingGuide / 6;
            //TODO: Get random pokemon from API with thisPokemonRatingGuide
            team[i] = pokemon;
            currentEnemyTeamRating += pokemon.getPokemonRating();

           if (currentEnemyTeamRating >= enemyTeamRatingGuide) {
               break;
           }
        }
    }

    private void initializeButtons() {

        fightButton = (Button)findViewById(R.id.startButton);
        teamButton = (Button) findViewById(R.id.changeTeamButton);
        easyToggleButton = (ToggleButton) findViewById(R.id.easyButton);
        mediumToggleButton = (ToggleButton) findViewById(R.id.mediumButton);
        hardToggleButton = (ToggleButton) findViewById(R.id.hardButton);

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pokemon[] enemyTeam = generateEnemyTeam();
                // TODO: Pass enemyTeam to BattleActivity
                Intent in=new Intent(PreBattleActivity.this, BattleActivity.class);
                startActivity(in);
            }
        });

        teamButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PreBattleActivity.this, TeamBuilder.class);
                startActivity(in);
            }
        });

        easyToggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mediumToggleButton.setChecked(false);
                hardToggleButton.setChecked(false);
            }
        });

        mediumToggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                easyToggleButton.setChecked(false);
                hardToggleButton.setChecked(false);
            }
        });

        hardToggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                easyToggleButton.setChecked(false);
                mediumToggleButton.setChecked(false);
            }
        });
    }

}
