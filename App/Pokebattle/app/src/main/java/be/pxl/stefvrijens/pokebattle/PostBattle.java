package be.pxl.stefvrijens.pokebattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;
import be.pxl.stefvrijens.pokebattle.services.InternalStorage;

public class PostBattle extends AppCompatActivity {
    boolean playerWon;
    Button backToMenuButton;
    int coinsWon;
    Player playerData;
    Pokemon[] enemyTeam;
    int enemyRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_battle);
        initializeButton();

        playerWon = getIntent().getBooleanExtra("PlayerWon", true);
        if (playerWon) {
            try {
                playerData = (Player)InternalStorage.readObject(this, "PlayerData");
            } catch (Exception ex) {
                System.out.println("Error reading playerData: " + ex.getMessage());
            }
            enemyTeam = (Pokemon[])getIntent().getSerializableExtra("EnemyTeam");
            coinsWon = calculateWonCoins();
            playerData.setOwnedCoins(playerData.getOwnedCoins() + coinsWon);

            try {
                InternalStorage.writeObject(this, "PlayerData", playerData);
            } catch (Exception ex) {
                System.out.println("Error writing playerData: " + ex.getMessage());
            }
        }

        updateDataBindings();

        backToMenuButton.setEnabled(true);
    }

    private void updateDataBindings() {
        TextView title = (TextView) findViewById(R.id.youWonTitle);
        TextView yourRating = (TextView) findViewById(R.id.teamRatingValue);
        TextView opponentRating = (TextView) findViewById(R.id.opponentRatingValue);
        TextView coinsWonText = (TextView) findViewById(R.id.coinsWonText);

        yourRating.setText("" + playerData.getTeamRating());
        opponentRating.setText("" + enemyRating);
        if (playerWon) {
            title.setText("You won!");
            coinsWonText.setText("You won " + coinsWon + " coins!");
        } else {
            title.setText("You lost...");
            coinsWonText.setText("Better luck next time.");
        }
    }

    private void initializeButton() {
        backToMenuButton = (Button)findViewById(R.id.backToMenuButton);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PostBattle.this, MainMenuActivity.class);
                startActivity(in);
            }
        });
        backToMenuButton.setEnabled(false);
    }

    private int calculateWonCoins() {
        int playerTeamRating = playerData.getTeamRating();
        for (int i = 0; i < enemyTeam.length; i++) {
            enemyRating += enemyTeam[i].getPokemonRating();
        }

        return (int)(40 + (10 * (1 + (enemyRating - playerTeamRating) / 100.0)));
    }
}
