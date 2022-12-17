package nl.aniketic.survival.game.sound;

import nl.aniketic.survival.engine.sound.Sound;

public class SoundControllerUtil {

    public static void play(SoundFx soundFx) {
        Sound sound = soundFx.getSound();
        sound.loadClip();
        sound.play();
    }
}
