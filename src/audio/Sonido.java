package audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sonido {
	private Clip clip;
	
	public Sonido() {
		File file = new File("./src/audio/SonidoLosSimpsons.wav");
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void mutear() {
		clip.stop();
	}
	
	public void desmutear() {
		clip.start();
	}
}
