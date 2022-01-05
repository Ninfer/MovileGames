package dadm.scaffold.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    public static TextView scoreText, yourScoreText;
    public static Button mainMenu, playAgain;
    public int scoreResult;

    public ScoreFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        mainMenu = rootView.findViewById(R.id.btn_main_menu);
        mainMenu.setText("MAIN MENU");

        scoreText = rootView.findViewById(R.id.text_score_result);
        yourScoreText = rootView.findViewById(R.id.text_your_score);
        yourScoreText.setText("Your score is:");

        SharedPreferences sp = getContext().getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
        scoreResult = sp.getInt("score", 0);

        scoreText.setText(String.valueOf(scoreResult));



        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ScaffoldActivity) getActivity()).startGame();
            }
        });
        view.findViewById(R.id.btn_main_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ScaffoldActivity) getActivity()).mainMenu();
            }
        });

        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.button_pulse);
        view.findViewById(R.id.btn_play_again).startAnimation(pulseAnimation);
        view.findViewById(R.id.btn_main_menu).startAnimation(pulseAnimation);
    }
}
