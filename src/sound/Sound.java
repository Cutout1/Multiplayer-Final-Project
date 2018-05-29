package sound;

import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;

public enum Sound {
	
	CHEEKIBREEKI("Cheeki Breeki.wav");
	//SNAKECRASH("crash.wav"),
	
	
	
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume volume = Volume.LOW;
	
	private Clip clip;
	   
	   Sound(String soundFileName) {
	      try {
	         URL url = this.getClass().getClassLoader().getResource(soundFileName);
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	         clip = AudioSystem.getClip();
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	 
	   public void play() {
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning()) {
	            clip.stop();  
	         }
	         clip.setFramePosition(0);
	         clip.start();   
	      }
	   }
}