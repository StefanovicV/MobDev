package be.pxl.stefvrijens.pokebattle;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TeamBuilder extends AppCompatActivity implements SelectedpokemonStats.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_builder);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
