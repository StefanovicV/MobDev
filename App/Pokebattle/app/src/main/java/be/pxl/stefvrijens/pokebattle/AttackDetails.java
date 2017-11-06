package be.pxl.stefvrijens.pokebattle;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import be.pxl.stefvrijens.pokebattle.domainclasses.Attack;
import be.pxl.stefvrijens.pokebattle.domainclasses.Player;


public class AttackDetails extends Fragment implements AdapterView.OnItemSelectedListener {
    Player playerData;
    Button changeButton;
    Spinner attackSelecter;
    Attack selectedAttack;
    EditAttack attackEditInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Get playerData from LOCALSTORAGE
        // TODO: Databind playerData.attacks to spinner
        final View view = inflater.inflate(R.layout.fragment_attack_details, container, false);
        initializeButtons(view);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity) {
            a = (Activity)context;
        } else {
            a=null;
        }

        try {
            attackEditInterface = (EditAttack)a;
        } catch (ClassCastException ex) {
            throw new ClassCastException(a.toString() + " has not implemented EditAttack");
        }
    }

    private void initializeButtons(View view) {
        changeButton = (Button) view.findViewById(R.id.newAttackConfirmButton);
        attackSelecter = (Spinner) view.findViewById(R.id.newAttackSelector);

        attackSelecter.setOnItemSelectedListener(this);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditConfirmClick(v);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedAttack = (Attack) parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onEditConfirmClick(View view) {
        attackEditInterface.onEditConfirm(view, selectedAttack);
    }

    public interface EditAttack {
        void onEditConfirm(View view, Attack attack);
    }


}
