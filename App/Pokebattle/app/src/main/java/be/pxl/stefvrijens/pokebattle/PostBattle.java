package be.pxl.stefvrijens.pokebattle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PostBattle extends AppCompatActivity {
    boolean playerWon;
    int coinsWon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_battle);

        //TODO: get playerWon variable from previous activity
        if (playerWon) {
            coinsWon = calculateWonCoins();
        }
        //TODO: LOCALSTORAGE: add coinsWon to player.ownedCoins
        //TODO: initialize back to mainMenu button
    }

    private int calculateWonCoins() {
        int playerTeamRating = 0;
        int opponentTeamRating = 0;
        int coins;
        //TODO: Get current teamRating from localstorage
        //TODO: Get opponentTeamRating from previous activity


        return coins = (int)(40 + (10 * (1 + (opponentTeamRating - playerTeamRating) / 100.0)));
    }
}
