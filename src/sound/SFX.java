package sound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * simple class for playing sfx.
 * @author hartleneal
 *
 */
public class SFX {

	/**
	 * plays the file specified via the path specified in the supplied String.
	 * @param file
	 */
 public SFX(String file){

	 File music = new File(file);

		try {
			Clip clip = AudioSystem.getClip();
			clip.open((AudioSystem.getAudioInputStream(music)));
	        clip.start();
	        Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch (Exception e) {

			e.printStackTrace();
		}

 }
}
