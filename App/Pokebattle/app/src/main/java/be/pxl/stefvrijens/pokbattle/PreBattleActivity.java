package be.pxl.stefvrijens.pokbattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreBattleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_battle);
    }

    public void goToTeamBuilder(View view) {
        Intent intent = new Intent (this, TeamBuilder.class);
        startActivity(intent);
    }

    public void goToFight(View view) {
        Intent intent = new Intent (this, BattleActivity.class);
        startActivity(intent);
    }
}
