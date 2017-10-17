package be.pxl.stefvrijens.pokebattle;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class TeamBuilder extends AppCompatActivity implements SelectedpokemonStats.OnFragmentInteractionListener, SelectedpokemonAttacks.OnEditButtonClick {
    ToggleButton statsToggleButton;
    ToggleButton attacksToggleButton;
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_builder);
        manager = getFragmentManager();

        statsToggleButton = (ToggleButton) findViewById(R.id.statsToggle);
        attacksToggleButton = (ToggleButton)findViewById(R.id.attacksToggle);

        statsToggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attacksToggleButton.setChecked(false);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.teamBuilderFragment, new SelectedpokemonStats());
                transaction.commit();
            }
        });

        attacksToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statsToggleButton.setChecked(false);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.teamBuilderFragment, new SelectedpokemonAttacks());
                transaction.commit();
            }
        });
    }

    @Override
    public void onButtonClick(View view, int attackNumber) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.teamBuilderFragment, new AttackDetails());
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
