package dadm.scaffold.counter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.space.GameController;

public class GameMenuFragment extends BaseFragment {

    public GameEngine theGameEngine;
    public GameController theGameController;

    public static TextView pauseText;
    public static Button pause, resume;

    public GameMenuFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_menu, container, false);

        theGameEngine = ((ScaffoldActivity) getActivity()).getGameEngine();
        theGameController = ((ScaffoldActivity) getActivity()).getGameController();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_resume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theGameEngine.resumeGame();
                theGameController.startStop();

                Fragment frag = getFragmentManager().findFragmentById(R.id.menu_layout);
                getFragmentManager().beginTransaction().remove(frag).commit();
            }
        });
        view.findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theGameEngine.stopGame();
                ((ScaffoldActivity)getActivity()).mainMenu();


                Fragment frag = getFragmentManager().findFragmentById(R.id.menu_layout);
                getFragmentManager().beginTransaction().remove(frag).commit();


            }
        });
        /*
        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.button_pulse);
        view.findViewById(R.id.btn_stop).startAnimation(pulseAnimation);
        view.findViewById(R.id.btn_resume).startAnimation(pulseAnimation);
        */
    }

    @Override
    public boolean onBackPressed() {
        theGameEngine.resumeGame();
        theGameController.startStop();

        Fragment frag = getFragmentManager().findFragmentById(R.id.menu_layout);
        getFragmentManager().beginTransaction().remove(frag).commit();

        return true;
    }
}