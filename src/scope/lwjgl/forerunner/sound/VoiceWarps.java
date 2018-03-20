package scope.lwjgl.forerunner.sound;

public class VoiceWarps {
	
	public static VoiceWarpPreset sandspro = new VoiceWarpPreset("sandspro", 0.9f);
	
	public static VoiceWarp vtSandspro = new VoiceWarp(Sounds.vtSandspro, sandspro);
	public static VoiceWarp vtSandsproClip = new VoiceWarp(Sounds.vtSandsproClip, sandspro);
	public static VoiceWarp vtSandsproAllLetters = new VoiceWarp(Sounds.vtSandsproAllLetters, sandspro);

}
