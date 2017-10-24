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

    OnFightButtonClick buttonClickInterface;
    Button fightButtonClick;

    public BattleChoice() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_battle_choice, container, false);
        fightButtonClick = (Button) view.findViewById(R.id.attackButton);

        fightButtonClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fightButtonClick(v);
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
            buttonClickInterface = (OnFightButtonClick)a;
        } catch (ClassCastException ex){
            throw new ClassCastException(a.toString() + " has not implemented buttonClickInterface");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void fightButtonClick(View v){
        buttonClickInterface.onButtonClick(v);
    }

    public interface OnFightButtonClick{
        void onButtonClick(View view);
    }
}
