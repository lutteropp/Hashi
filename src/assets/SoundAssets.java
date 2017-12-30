package assets;

import java.io.File;

import jaco.mp3.player.MP3Player;

/**
 * A class for managing the sound assets of the Hashiwokakero game. All sounds
 * files used are distributed under various Creative Commons Licenses and all of
 * them are free for non-commercial use.
 * 
 * @author Sarah Lutteropp
 */
public class SoundAssets {
	// Play music using https://sourceforge.net/projects/jacomp3player/
	// See example here:
	// https://sites.google.com/site/teachmemrxymon/java/how-to-use-mp3player-class

	// Button sound effect files from https://www.soundjay.com
	public static final MP3Player connectSound = new MP3Player(
			new File(ClassLoader.getSystemResource("assets/media/button-20.mp3").getFile()));
	public static final MP3Player disconnectSound = new MP3Player(
			new File(ClassLoader.getSystemResource("assets/media/button-46.mp3").getFile()));
	// Title music from https://opengameart.org/content/zombies-march
	public static final MP3Player titleMusic = new MP3Player(
			new File(ClassLoader.getSystemResource("assets/media/ZombiesAreComing.mp3").getFile()));
	// Game music from http://nosoapradio.us/
	public static final MP3Player gameMusic = new MP3Player(
			new File(ClassLoader.getSystemResource("assets/media/DST-DayBreak.mp3").getFile()));
	// Winning music from http://www.orangefreesounds.com/
	public static final MP3Player winningMusic = new MP3Player(
			new File(ClassLoader.getSystemResource("assets/media/Ta-da-orchestra-fanfare.mp3").getFile()));
}
