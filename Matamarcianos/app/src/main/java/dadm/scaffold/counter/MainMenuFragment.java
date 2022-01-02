package dadm.scaffold.counter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;


public class MainMenuFragment extends BaseFragment implements View.OnClickListener {
    public MainMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_start).setOnClickListener(this);

        Animation pulseAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.button_pulse);
        view.findViewById(R.id.btn_start).startAnimation(pulseAnimation);

        Animation titleAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.title_enter);
        view.findViewById(R.id.GameTitle).startAnimation(titleAnimation);

        Animation subtitleAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.subtitle_enter);
        view.findViewById(R.id.GameSubtitle).startAnimation(subtitleAnimation);
    }

    @Override
    public void onClick(View v) {
        ((ScaffoldActivity)getActivity()).startGame();
    }
}
