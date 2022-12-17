package nl.aniketic.survival.game.sound;

import nl.aniketic.survival.engine.sound.Sound;

public enum SoundFx {
    DOOR("/sound/fx/door.wav"),
    PICKUP("/sound/fx/pickup.wav");

    private final Sound sound;

    SoundFx(String path) {
        sound = new Sound(path);
    }

    public Sound getSound() {
        return sound;
    }
}
