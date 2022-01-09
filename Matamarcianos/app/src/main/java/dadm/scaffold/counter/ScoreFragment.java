package dadm.scaffold.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;

public class ScoreFragment extends BaseFragment {

    public final int MAX_SCORE_TO_WIN = 35000;

    public static TextView scoreText, yourScoreTextWin, yourScoreTextLose, record;
    public static Button mainMenu, playAgain;

    public int  enemiesKilled, score, lives, maxScore;

    public ScoreFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);


        mainMenu = rootView.findViewById(R.id.btn_main_menu);
        scoreText = rootView.findViewById(R.id.text_score_result);
        yourScoreTextWin = rootView.findViewById(R.id.text_your_score_win);
        yourScoreTextLose = rootView.findViewById(R.id.text_your_score_lose);
        record = rootView.findViewById(R.id.record_text);

        record.setVisibility(View.INVISIBLE);
        yourScoreTextLose.setVisibility(View.INVISIBLE);
        yourScoreTextWin.setVisibility(View.INVISIBLE);

        SharedPreferences sp = getContext().getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
        score = sp.getInt("score", 0);
        enemiesKilled = sp.getInt("enemies", 0);
        lives = sp.getInt("lives", 0);
        maxScore = sp.getInt("maxScore", 0);

        makeFinalScreen(rootView);

        if (score > maxScore){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("maxScore", score);
            editor.commit();
            record.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ScaffoldActivity) getActivity()).startGame();
                Fragment frag = getFragmentManager().findFragmentById(R.id.menu_layout);
                getFragmentManager().beginTransaction().remove(frag).commit();
            }
        });
        view.findViewById(R.id.btn_main_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ScaffoldActivity) getActivity()).mainMenu();
                Fragment frag = getFragmentManager().findFragmentById(R.id.menu_layout);
                getFragmentManager().beginTransaction().remove(frag).commit();
            }
        });

        /*
        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.button_pulse);
        view.findViewById(R.id.btn_play_again).startAnimation(pulseAnimation);
        view.findViewById(R.id.btn_main_menu).startAnimation(pulseAnimation);
         */
    }

    public boolean onBackPressed() {
        ((ScaffoldActivity) getActivity()).mainMenu();
        Fragment frag = getFragmentManager().findFragmentById(R.id.menu_layout);
        getFragmentManager().beginTransaction().remove(frag).commit();
        return true;
    }

    public void makeFinalScreen(View rootView){

        if (score >= MAX_SCORE_TO_WIN){
            rootView.setBackground(getResources().getDrawable(R.drawable.rounded_shape_score_green));
            rootView.getBackground().setAlpha(127);

            yourScoreTextWin.setVisibility(View.VISIBLE);
        } else{
            rootView.setBackground(getResources().getDrawable(R.drawable.rounded_shape_score_red));
            rootView.getBackground().setAlpha(127);

            yourScoreTextLose.setVisibility(View.VISIBLE);
        }

        scoreText.setText(String.valueOf(score) + " / " + String.valueOf(MAX_SCORE_TO_WIN));
    }
}
