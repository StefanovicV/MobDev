package be.pxl.stefvrijens.pokebattle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class PreBattleActivity extends AppCompatActivity {
    Button fightButton;
    Button teamButton;

    ToggleButton easyToggleButton;
    ToggleButton mediumToggleButton;
    ToggleButton hardToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_battle);

        fightButton = (Button)findViewById(R.id.startButton);
        teamButton = (Button) findViewById(R.id.changeTeamButton);

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        easyToggleButton = (ToggleButton) findViewById(R.id.easyButton);
        mediumToggleButton = (ToggleButton) findViewById(R.id.mediumButton);
        hardToggleButton = (ToggleButton) findViewById(R.id.hardButton);

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
