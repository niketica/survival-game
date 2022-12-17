package nl.aniketic.survival.controls;

import nl.aniketic.survival.engine.controls.KeyHandler;

import java.awt.event.KeyEvent;

public class EditorKeyListener extends KeyHandler {

    public EditorKeyListener() {
        putKey(KeyEvent.VK_W, Key.UP.getKeyObserver());
        putKey(KeyEvent.VK_A, Key.LEFT.getKeyObserver());
        putKey(KeyEvent.VK_S, Key.DOWN.getKeyObserver());
        putKey(KeyEvent.VK_D, Key.RIGHT.getKeyObserver());
    }
}
