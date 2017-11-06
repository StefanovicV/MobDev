package be.pxl.stefvrijens.pokebattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import be.pxl.stefvrijens.pokebattle.domainclasses.Player;

public class PostBattle extends AppCompatActivity {
    boolean playerWon;
    Button backToMenuButton;
    int coinsWon;
    Player playerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_battle);
        initializeButton();

        //TODO: get playerWon variable from previous activity
        if (playerWon) {
            coinsWon = calculateWonCoins();
        }
        // TODO: LOCALSTORAGE get playerData
        playerData.setOwnedCoins(playerData.getOwnedCoins() + coinsWon);
        // TODO: LOCALSTORAGE update playerData

        backToMenuButton.setEnabled(true);
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
        int playerTeamRating;
        int opponentTeamRating = 0;
        int coins;

        playerTeamRating = playerData.getTeamRating();

        //TODO: Get opponentTeamRating from previous activity

        return coins = (int)(40 + (10 * (1 + (opponentTeamRating - playerTeamRating) / 100.0)));
    }
}
