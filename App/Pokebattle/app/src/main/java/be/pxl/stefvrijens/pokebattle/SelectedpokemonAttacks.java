package be.pxl.stefvrijens.pokebattle;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SelectedpokemonAttacks extends Fragment {
    // YORAN 2. De interface wordt dan hier gedeclareerd
    OnEditButtonClick buttonClickInterface;
    Button attackEditButton1;
    Button attackEditButton2;
    Button attackEditButton3;
    Button attackEditButton4;


    public SelectedpokemonAttacks() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if(context instanceof Activity) {
            a=(Activity)context;
        } else {
            a=null;
        }

        try {
            // YORAN 3. De activity waar deze fragment in zit, wordt aan de declaratie in het vorige puntje gekoppeld (die if & try zijn om te controleren of de activity die interface wel implementeert)
            buttonClickInterface = (OnEditButtonClick)a;
        } catch (ClassCastException ex) {
            throw new ClassCastException(a.toString() + " has not implemented buttonClickInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_selectedpokemon_attacks, container, false);
        attackEditButton1 = (Button) view.findViewById(R.id.attack1EditButton);
        attackEditButton2 = (Button) view.findViewById(R.id.attack2EditButton);
        attackEditButton3 = (Button) view.findViewById(R.id.attack3EditButton);
        attackEditButton4 = (Button) view.findViewById(R.id.attack4EditButton);

        attackEditButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClick(v, 1);
            }
        });
        attackEditButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClick(v, 2);
            }
        });
        attackEditButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClick(v, 3);
            }
        });
        attackEditButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editButtonClick(v, 4);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
	
    // YORAN: 4. Dit is de functie die aangeroepen wordt door de knopkes binnen het fragment, de knopkes aan deze functie koppelen heb ik hierboven gedaan (setOnClickListener), verdere uitleg in de TeamBuilder class
    public void editButtonClick(View v, int attackNumber) {
        buttonClickInterface.onButtonClick(v, attackNumber);
    }

    // YORAN: 1. deze interface aanmaken
    public interface OnEditButtonClick {
        void onButtonClick(View view, int attackNumber);
    }
}
