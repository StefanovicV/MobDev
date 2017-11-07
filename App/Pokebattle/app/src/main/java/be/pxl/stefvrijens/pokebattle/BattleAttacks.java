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

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Pokemon;


public class BattleAttacks extends Fragment {
    OnAttackButtonClick buttonClickInterface;
    Button[] buttons;


    public BattleAttacks() {
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
            buttonClickInterface = (OnAttackButtonClick)a;
        } catch (ClassCastException ex) {
            throw new ClassCastException(a.toString() + " has not implemented buttonClickInterface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_battle_attacks, container, false);
        buttons = new Button[4];

        buttons[0] = (Button) view.findViewById(R.id.AttackButton1);
        buttons[1] = (Button) view.findViewById(R.id.AttackButton2);
        buttons[2] = (Button) view.findViewById(R.id.AttackButton3);
        buttons[3] = (Button) view.findViewById(R.id.AttackButton4);


        buttons[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attackButtonClick(v, 0);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attackButtonClick(v, 1);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attackButtonClick(v, 2);
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attackButtonClick(v, 3);
            }
        });

        return view;
    }

    public void attackButtonClick(View v, int attackNumber) {
        buttonClickInterface.buttonClickHandler(v, attackNumber);
    }

    public interface OnAttackButtonClick {
        void buttonClickHandler(View view, int attackNumber);
    }

    public void updateButtonDatabinding(Pokemon playerPokemon) {
        Attack[] attacks = playerPokemon.getAttacks();
        for (int i = 0; i < attacks.length; i++) {
            if (attacks[i] != null) {
                buttons[i].setText(attacks[i].getName());
                buttons[i].setEnabled(true);
            }
        }
        if (attacks.length < 4) {
            for (int i = attacks.length; i < 4; i++) {
                buttons[i].setText("");
                buttons[i].setEnabled(false);
            }
        }

    }

}
