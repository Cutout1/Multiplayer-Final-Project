package sound;
//Class description: This is the sound enum and it includes the implementation of abstract methods.
import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;

public enum Sound {
	
	CHEEKIBREEKI("sounds/Cheeki Breeki.wav"),
	HIT("sounds/hit.wav"),
	MISS("sounds/miss.wav"),
	ROTV("sounds/Ride_Of_The_Valkyries.wav");
	//SNAKECRASH("crash.wav"),
	
	
	
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume volume = Volume.LOW;
	
	private Clip clip;
	   
	   Sound(String soundFileName) {   //create the "Sound object" --> The parameters should be the name of the file
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
	 
	   public void play() {  //do [soundobjectucreated].play(); and fingers crossed it should work
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning()) {
	            clip.stop();  
	         }
	         clip.setFramePosition(0);
	         clip.start();   
	      }
	   }
	   
	   public void stop() {   //stop playing the track!
		   clip.stop();
		   clip.setFramePosition(0);
	   }
	   
	   public void loop() {   //loop the track till stopped
		   if (volume != Volume.MUTE) {
		         if (clip.isRunning()) { 
		            clip.stop();  
		         }
		         clip.setFramePosition(0);
		         clip.start();   
		      }
		   if (clip.getFramePosition() == 0) {
			   if (volume != Volume.MUTE) {
			         if (clip.isRunning()) {
			            clip.stop();  
			         }
			         clip.setFramePosition(0);
			         clip.start();   
			      } 
		   }
	   }
}
