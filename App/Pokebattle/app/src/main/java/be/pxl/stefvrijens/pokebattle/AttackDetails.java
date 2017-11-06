package be.pxl.stefvrijens.pokebattle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.pxl.stefvrijens.pokebattle.domainclasses.Player;


public class AttackDetails extends Fragment {
    Player playerData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Get playerData from LOCALSTORAGE
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attack_details, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
