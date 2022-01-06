package dadm.scaffold;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dadm.scaffold.counter.GameMenuFragment;
import dadm.scaffold.counter.MainMenuFragment;
import dadm.scaffold.counter.ScoreFragment;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.sound.SoundManager;
import dadm.scaffold.space.GameController;

public class ScaffoldActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "content";

    private GameEngine gameEngine;
    private GameController gameController;

    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaffold);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMenuFragment(), TAG_FRAGMENT)
                    .commit();
        }
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundManager = new SoundManager(getApplicationContext());
        SharedPreferences sp = getSharedPreferences("defaultSettings", 0);
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void startGame() {
        // Navigate the the game fragment, which makes the start automatically
        navigateToFragment( new GameFragment());
    }

    public void mainMenu(){
        navigateToFragment( new MainMenuFragment());
    }

    public void scoreMenu(){
        navigateToFragmentMenu( new ScoreFragment());
    }
    public void gameMenu(GameEngine gameEngine, GameController gameController){
        setGameEngine(gameEngine);
        setGameController(gameController);
        navigateToFragmentMenu( new GameMenuFragment());
    }

    private void navigateToFragment(BaseFragment dst) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dst, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    private void navigateToFragmentMenu(BaseFragment dst) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.menu_enter, R.anim.menu_exit, R.anim.menu_enter, R.anim.menu_exit)
                .replace(R.id.menu_layout, dst, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        final BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void navigateBack() {
        // Do a push on the navigation history
        super.onBackPressed();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
            else {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    public GameEngine getGameEngine(){
        return this.gameEngine;
    }
    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }
    public GameController getGameController(){
        return this.gameController;
    }
}
