package nl.aniketic.survival.engine.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public abstract class KeyHandler implements KeyListener {

    private final Map<Integer, KeyObserver> keyMap = new HashMap<>();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyObserver key = keyMap.get(e.getKeyCode());
        if (key != null) {
            key.setPressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyObserver key = keyMap.get(e.getKeyCode());
        if (key != null) {
            key.setPressed(false);
        }
    }

    public void putKey(int keyCode, KeyObserver key) {
        keyMap.put(keyCode, key);
    }
}
