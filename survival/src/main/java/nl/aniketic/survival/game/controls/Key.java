package nl.aniketic.survival.game.controls;

import nl.aniketic.survival.engine.controls.KeyObserver;

public enum Key {
    UP,
    LEFT,
    RIGHT,
    DOWN;

    private final KeyObserver keyObserver;

    Key() {
        keyObserver = new KeyObserver(this.name());
    }

    public KeyObserver getKeyObserver() {
        return keyObserver;
    }

    public boolean isPressed() {
        return keyObserver.isPressed();
    }
}
