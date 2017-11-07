package be.pxl.stefvrijens.pokebattle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.services.ImageService;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;

public class PreBattleActivity extends AppCompatActivity {
    Button fightButton;
    Button teamButton;

    ToggleButton easyToggleButton;
    ToggleButton mediumToggleButton;
    ToggleButton hardToggleButton;

    Player playerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_battle);
        initializeButtons();
        try {
            playerData = (Player) InternalStorage.readObject(this, "PlayerData");
            bindData();
        } catch (Exception ex) {
            System.err.println("Error reading playerData: " + ex.getMessage());
        }
    }

    private Pokemon[] generateEnemyTeam() {
        int playerTeamRating;
        int currentEnemyTeamRating = 0;
        int enemyTeamRatingGuide;
        Pokemon[] team = new Pokemon[6];

        playerTeamRating = playerData.getTeamRating();

        if (easyToggleButton.isChecked()) {
            enemyTeamRatingGuide = playerTeamRating - 200;
        } else if (mediumToggleButton.isChecked()) {
            enemyTeamRatingGuide = playerTeamRating;
        } else {
            enemyTeamRatingGuide = playerTeamRating + 200;
        }

        for (int i = 0; i < 6; i++) {
            Pokemon pokemon = null;
            int thisPokemonRatingGuide = enemyTeamRatingGuide / 6;

            //TODO: Replace this with random pokemon from API with thisPokemonRatingGuide
            pokemon = Pokemon.generateTestPokemon();
            team[i] = pokemon;
            currentEnemyTeamRating += pokemon.getPokemonRating();

           if (currentEnemyTeamRating >= enemyTeamRatingGuide) {
               break;
           }
        }
        return team;
    }

    private void bindData() {
        ImageView[] imageViews = new ImageView[] {
                (ImageView)findViewById(R.id.pokemon1Image),
                (ImageView)findViewById(R.id.pokemon2Image),
                (ImageView)findViewById(R.id.pokemon3Image),
                (ImageView)findViewById(R.id.pokemon4Image),
                (ImageView)findViewById(R.id.pokemon5Image),
                (ImageView)findViewById(R.id.pokemon6Image)
        };
        TextView tv = (TextView)findViewById(R.id.teamRatingPreBattle);

        Pokemon[] playerTeam = playerData.getTeam();
        for (int i = 0; i < playerTeam.length; i++) {
            ImageService is = new ImageService();
            Bitmap image = is.getImageFromWeb(playerTeam[i].getSpecies().getImageUrl(), this);
            imageViews[i].setImageBitmap(image);
        }

        tv.setText("Current Teamrating: " + playerData.getTeamRating());
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
                if (easyToggleButton.isChecked() || mediumToggleButton.isChecked() || hardToggleButton.isChecked()) {
                    Pokemon[] enemyTeam = generateEnemyTeam();
                    Intent in=new Intent(PreBattleActivity.this, BattleActivity.class);
                    in.putExtra("EnemyTeam", enemyTeam);
                    startActivity(in);
                }
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
