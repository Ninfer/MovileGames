package dadm.scaffold.sound;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

public class SoundInfo {

	private static final long FLOOD_TIMEOUT = 500;
	
	private static final Random random = new Random();
	
	private int[] soundId;
	private long msoundId;

	public SoundInfo(Context context, SoundPool engine, String[] filename) {
		soundId = new int [filename.length];
		for (int i=0; i<filename.length; i++) {
			try {
				soundId[i] = engine.load(context.getAssets().openFd("music/"+filename[i]), 1);
			} catch (IOException e) {
				Log.e("SoundInfo", "Sound not found: "+filename[i]);
				e.printStackTrace();
			}			
		}
	}

	public void play(SoundPool engineSound) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - msoundId > FLOOD_TIMEOUT) {
			msoundId = currentTime;
			int posToPlay = random.nextInt(soundId.length);
			engineSound.play(soundId[posToPlay], 1.0f, 1.0f, 0, 0, 1.0f);
		}
	}

}
