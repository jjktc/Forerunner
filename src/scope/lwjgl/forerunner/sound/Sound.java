package scope.lwjgl.forerunner.sound;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

import scope.lwjgl.forerunner.game.settings.Options;

public class Sound {

	public Audio sound;

	public Sound(String type, String location) {
		try {
			sound = AudioLoader.getAudio(type, Sound.class.getResourceAsStream("/" + location));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sound(String location) {
		try {
			sound = AudioLoader.getAudio("OGG", Sound.class.getResourceAsStream("/" + location));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sound(String location, boolean loop) {
		try {
			sound = AudioLoader.getAudio("OGG", Sound.class.getResourceAsStream("/" + location));
			if (loop) {
				// alSourcei(sound.get(0), AL_LOOPING, AL_TRUE );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playE(boolean loop) {
		sound.playAsSoundEffect(1.0f, Options.volume, loop);
	}

	public void playE() {
		sound.playAsSoundEffect(1.0f, Options.volume, false);
	}

	public void playE(float a, float b, boolean loop) {
		sound.playAsSoundEffect(a, b * Options.volume, loop);
	}

	public void playE(float a, float b) {
		sound.playAsSoundEffect(a, b * Options.volume, false);
	}

	public void playM(float a, float b, boolean loop) {
		sound.playAsMusic(a, b * Options.volume, loop);
	}

	public void stop() {
		sound.stop();
	}

	public static void stopAll() {
		Sounds.first_flight.stop();
	}

}
