package nl.aniketic.survival.engine.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    private final URL soundURL;
    private Clip clip;

    public Sound(String path) {
        soundURL = getClass().getResource(path);
    }

    public void loadClip() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip == null) {
            return;
        }
        clip.stop();
    }

    public boolean isRunning() {
        return clip != null && clip.isRunning();
    }
}
