package nl.aniketic.survival.game.sound;

import nl.aniketic.survival.engine.sound.Sound;

public enum SoundLoop {
    HORRIBLE("/sound/loops/horrible_loop.wav"),
    SPOOKY("/sound/loops/spooky_loop.wav");

    private final Sound sound;

    SoundLoop(String path) {
        sound = new Sound(path);
    }

    public Sound getSound() {
        return sound;
    }
}
