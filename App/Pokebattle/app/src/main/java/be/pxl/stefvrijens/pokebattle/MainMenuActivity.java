package be.pxl.stefvrijens.pokebattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    Button shopButton;
    Button fightButton;
    Button myTeamButton;
//    Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        shopButton = (Button) findViewById(R.id.shopButton);
        fightButton = (Button) findViewById(R.id.fightButton);
        myTeamButton = (Button) findViewById(R.id.myTeamButton);
//        logOutButton = (Button) findViewById(R.id.logOutButton);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainMenuActivity.this, Shop.class);
                startActivity(in);
            }
        });
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainMenuActivity.this, PreBattleActivity.class);
                startActivity(in);
            }
        });
        myTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainMenuActivity.this, TeamBuilder.class);
                startActivity(in);
            }
        });
//        logOutButton.setOnClickListener(new View.OnClickListener() {
//
//        });
    }


}
