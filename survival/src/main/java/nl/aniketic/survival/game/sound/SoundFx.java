package nl.aniketic.survival.game.sound;

import nl.aniketic.survival.engine.sound.Sound;

public enum SoundFx {
    DOOR("/sound/fx/door.wav"),
    PICKUP("/sound/fx/pickup.wav"),
    BAT_ATTACK("/sound/fx/bat_attack.wav"),
    ZOMBIE_HIT("/sound/fx/zombie_hit.wav");

    private final Sound sound;

    SoundFx(String path) {
        sound = new Sound(path);
    }

    public Sound getSound() {
        return sound;
    }
}
