package scope.lwjgl.forerunner.sound;

public class VoiceWarp {
	
	public Sound sound;
	public float pitch;
	
	public VoiceWarp(Sound sound, float pitch) {
		this.sound = sound;
		this.pitch = pitch;
	}
	
	public VoiceWarp(Sound sound, VoiceWarpPreset vwp) {
		this.sound = sound;
		this.pitch = vwp.pitch;
	}
	
	public void play(float volume) {
		sound.playE(pitch, volume);
	}

}
