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

import org.json.JSONObject;

import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.services.ImageService;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;
import be.pxl.stefvrijens.pokebattle.services.PokemonService;

public class PreBattleActivity extends AppCompatActivity {
    Button fightButton;
    Button teamButton;

    ToggleButton easyToggleButton;
    ToggleButton mediumToggleButton;
    ToggleButton hardToggleButton;

    Player playerData;
    Pokemon[] enemyTeam = new Pokemon[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_battle);
        initializeButtons();
        try {
            playerData = (Player) InternalStorage.readObject(this, "PlayerData");
            System.out.println("Current Player Data: ");
            System.out.println("Coins: " + playerData.getOwnedCoins());
            bindData();
        } catch (Exception ex) {
            System.err.println("Error reading playerData: " + ex.getMessage());
        }
    }

    private void generateEnemyTeam() {
        int playerTeamRating;
        int enemyTeamRatingGuide;

        playerTeamRating = playerData.getTeamRating();

        if (easyToggleButton.isChecked()) {
            enemyTeamRatingGuide = playerTeamRating - 200;
        } else if (mediumToggleButton.isChecked()) {
            enemyTeamRatingGuide = playerTeamRating;
        } else {
            enemyTeamRatingGuide = playerTeamRating + 200;
        }

        PokemonService ps = new PokemonService();
        ps.GetTeamByRating(enemyTeamRatingGuide, this, new PreBattleCallback() {
            @Override
            public void onSuccess(Pokemon[] result) {
                enemyTeam = result;
                for (int i = 0; i < enemyTeam.length; i++) {
                    enemyTeam[i].resetCurrentHp();
                    System.out.println(enemyTeam[i].getSpecies().getName());
                }
                Intent in=new Intent(PreBattleActivity.this, BattleActivity.class);
                in.putExtra("EnemyTeam", enemyTeam);
                startActivity(in);
            }
        });
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
            is.getImageFromWeb(playerTeam[i].getSpecies().getImageUrl(), this, imageViews[i]);
        }

        tv.setText("Current Teamrating: " + playerData.getTeamRating());
    }

    public interface PreBattleCallback{
        void onSuccess(Pokemon[] result);
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
                    generateEnemyTeam();
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
