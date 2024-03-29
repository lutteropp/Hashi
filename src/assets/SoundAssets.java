package assets;

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

	public static final MP3Player connectSound = new MP3Player(ClassLoader.getSystemResource("assets/button-20.mp3"));
	public static final MP3Player disconnectSound = new MP3Player(
			ClassLoader.getSystemResource("assets/button-46.mp3"));
	// Title music from https://opengameart.org/content/zombies-march
	public static final MP3Player titleMusic = new MP3Player(
			ClassLoader.getSystemResource("assets/ZombiesAreComing.mp3"));
	// Game music from http://nosoapradio.us/
	public static final MP3Player gameMusic = new MP3Player(ClassLoader.getSystemResource("assets/DST-DayBreak.mp3"));
	// Winning music from http://www.orangefreesounds.com/
	public static final MP3Player winningMusic = new MP3Player(
			ClassLoader.getSystemResource("assets/Ta-da-orchestra-fanfare.mp3"));
}
