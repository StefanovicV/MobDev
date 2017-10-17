package be.pxl.stefvrijens.pokbattle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BattleActivity extends AppCompatActivity implements BattleVisuals.OnFragmentInteractionListener, BattleChoice.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void goToAttackStyles(View view) {
        
    }
}
