package be.pxl.stefvrijens.pokebattle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class BattleActivity extends AppCompatActivity implements BattleVisuals.OnFragmentInteractionListener, BattleChoice.OnFragmentInteractionListener, BattleAttacks.OnFragmentInteractionListener, BattleItems.OnFragmentInteractionListener, BattleSwitchPokemon.OnFragmentInteractionListener,
        BattleChoice.OnFightButtonClick, BattleChoice.OnUseItemButtonClick, BattleChoice.OnSwitchPokemonButtonClick {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        manager = getFragmentManager();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void doFightButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleAttacks());
        transaction.commit();
    }

    @Override
    public void doUseItemButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleItems());
        transaction.commit();
    }

    @Override
    public void doSwitchButtonClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("");
        transaction.replace(R.id.battleChoice, new BattleSwitchPokemon());
        transaction.commit();
    }
}
