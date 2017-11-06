package be.pxl.stefvrijens.pokebattle;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Player;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;

// YORAN 5. De interface die we net hebben aangemaakt hier geïmplementeerd
public class TeamBuilder extends AppCompatActivity implements SelectedpokemonAttacks.OnEditButtonClick, AdapterView.OnItemSelectedListener, AttackDetails.EditAttack {
    ToggleButton statsToggleButton;
    ToggleButton attacksToggleButton;
    ImageButton[] teamButtons;
    Spinner pokemonSelecter;
    Button evolveButton;
    FragmentManager manager;
    Player playerData;
    Pokemon selectedPokemon;
    int changedAttackNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_builder);
        manager = getFragmentManager();
        initializeButtons();
        // TODO: Get PlayerData from LOCALSTORAGE
        // TODO: Databind playerData.pokemon to spinner
        // TODO: Databind selectedPokemon to lower screen info.
    }

    private void evolveSelectedPokemon() {
        if (selectedPokemon.getSpecies().getEvolutionCost() > 0) {
            playerData.setOwnedCoins(playerData.getOwnedCoins() - selectedPokemon.getSpecies().getEvolutionCost());
            selectedPokemon.setSpecies(selectedPokemon.getSpecies().getNextEvolution());
            playerData.updatePokemon(selectedPokemon);
            // TODO: LOCALSTORAGE write playerData
        }
    }

    private void initializeButtons() {
        statsToggleButton = (ToggleButton)findViewById(R.id.statsToggle);
        attacksToggleButton = (ToggleButton)findViewById(R.id.attacksToggle);
        evolveButton = (Button)findViewById(R.id.evolveButton);
        pokemonSelecter = (Spinner)findViewById(R.id.yourPokemonList);
        teamButtons = new ImageButton[6];
        teamButtons[0] = (ImageButton)findViewById(R.id.activePokemonButton1);
        teamButtons[1] = (ImageButton)findViewById(R.id.activePokemonButton2);
        teamButtons[2] = (ImageButton)findViewById(R.id.activePokemonButton3);
        teamButtons[3] = (ImageButton)findViewById(R.id.activePokemonButton4);
        teamButtons[4] = (ImageButton)findViewById(R.id.activePokemonButton5);
        teamButtons[5] = (ImageButton)findViewById(R.id.activePokemonButton6);

        statsToggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attacksToggleButton.setChecked(false);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.teamBuilderFragment, new SelectedpokemonStats());
                transaction.commit();
            }
        });

        evolveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evolveSelectedPokemon();
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
        pokemonSelecter.setOnItemSelectedListener(this);

        for (int i = 0; i < teamButtons.length; i++) {
            final int j = i;
            teamButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pokemon[] currentTeam = playerData.getTeam();
                    currentTeam[j] = selectedPokemon;
                    playerData.setTeam(currentTeam);
                    //TODO: Write to localstorage
                }
            });
        }
    }

    // YORAN 6. Deze functie (uit de interface die we net hebben gemaakt) wordt nu aangeroepen als die ook aangeroepen wordt in het fragment.
    @Override
    public void onButtonClick(View view, int attackNumber) {
        changedAttackNumber = attackNumber;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.teamBuilderFragment, new AttackDetails());
        transaction.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPokemon = (Pokemon) parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onEditConfirm(View view, Attack attack) {
        Attack[] attacks = selectedPokemon.getAttacks();
        attacks[changedAttackNumber] = attack;
        selectedPokemon.setAttacks(attacks);
        // TODO: Write to localstorage
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.teamBuilderFragment, new SelectedpokemonAttacks());
        transaction.commit();
    }
}
