package be.pxl.stefvrijens.pokebattle;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BattleChoice extends Fragment {

    OnFightButtonClick buttonFightClickInterface;
    OnUseItemButtonClick buttonUseItemClickInterface;
    OnSwitchPokemonButtonClick buttonSwitchClickInterface;
    Button goToAttacksButtonClick;
    Button goToUseItemButtonClick;
    Button goToSwitchButtonClick;

    public BattleChoice() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_battle_choice, container, false);
        goToAttacksButtonClick = (Button) view.findViewById(R.id.attackButton);
        goToUseItemButtonClick = (Button) view.findViewById(R.id.itemButton);
        goToSwitchButtonClick = (Button) view.findViewById(R.id.switchButton);

        goToAttacksButtonClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fightButtonClick(v);
            }
        });

        goToUseItemButtonClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                useItemButtonClick(v);
            }
        });

        goToSwitchButtonClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchPokemonButtonClick(v);
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity) {
            a = (Activity)context;
        } else {
            a = null;
        }

        try {
            buttonFightClickInterface = (OnFightButtonClick)a;
            buttonUseItemClickInterface = (OnUseItemButtonClick)a;
            buttonSwitchClickInterface = (OnSwitchPokemonButtonClick)a;
        } catch (ClassCastException ex){
            throw new ClassCastException(a.toString() + " has not implemented buttonClickInterface");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void fightButtonClick(View v){
        buttonFightClickInterface.doFightButtonClick(v);
    }
    public void useItemButtonClick(View v) { buttonUseItemClickInterface.doUseItemButtonClick(v); }
    public void switchPokemonButtonClick(View v) { buttonSwitchClickInterface.doSwitchButtonClick(v); }

    public interface OnFightButtonClick{
        void doFightButtonClick(View view);
    }

    public interface OnUseItemButtonClick{
        void doUseItemButtonClick(View view);
    }

    public interface OnSwitchPokemonButtonClick{
        void doSwitchButtonClick(View view);
    }
}
