package sound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * class for playing a soundtrack.
 * @author hartleneal
 *
 */
public class SoundTrack {

	private Thread t1;
	private Boolean pause = false;
    private String song;
	/**
	 * creates a thread that runs a soundtrack in random order that will never end.
	 */
	public SoundTrack(){

		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
				if(!pause){
			   int random = (int) (Math.random()*5);
				File music = new File("src/music/"+random+".wav");

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
			}
		});

	}

	public SoundTrack(String s){
       song = s;
		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
				if(!pause){

				File music = new File(song);

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
			}
		});

	}
/**
 * checks wether soundtrack is initialised and then starts the thread that plays soundtrack.
 */
	public void StartMusic(){
		if(t1!=null){t1.start();}

	}
	/**
	 * music will stop playing after current song finishes
	 */
	public void stopMusic(){
		pause = true;

		}
    /**
     * music will restart with a random track
     */
	public void reStartMusic(){
	pause = false;
	}

	public void stop() {
		t1.interrupt();

	}

}
