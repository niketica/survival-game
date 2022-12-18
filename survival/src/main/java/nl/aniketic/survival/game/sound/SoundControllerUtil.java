package nl.aniketic.survival.game.sound;

import nl.aniketic.survival.engine.sound.Sound;

public class SoundControllerUtil {

    public static void play(SoundFx soundFx) {
        Sound sound = soundFx.getSound();
        sound.loadClip();
        sound.play();
    }

    public static void loop(SoundLoop soundLoop) {
        Sound sound = soundLoop.getSound();
        sound.loadClip();
        sound.setVolume(-20);
        sound.loop();
    }

    public static void stop(SoundLoop soundLoop) {
        Sound sound = soundLoop.getSound();
        sound.stop();
    }
}
