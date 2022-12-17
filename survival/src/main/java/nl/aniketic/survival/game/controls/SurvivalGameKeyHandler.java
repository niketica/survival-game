package nl.aniketic.survival.game.controls;

import nl.aniketic.survival.engine.controls.KeyHandler;

import java.awt.event.KeyEvent;

public class SurvivalGameKeyHandler extends KeyHandler {

    public SurvivalGameKeyHandler() {
        putKey(KeyEvent.VK_W, Key.UP.getKeyObserver());
        putKey(KeyEvent.VK_A, Key.LEFT.getKeyObserver());
        putKey(KeyEvent.VK_S, Key.DOWN.getKeyObserver());
        putKey(KeyEvent.VK_D, Key.RIGHT.getKeyObserver());
        putKey(KeyEvent.VK_SPACE, Key.SPACE.getKeyObserver());
        putKey(KeyEvent.VK_ESCAPE, Key.ESC.getKeyObserver());
        putKey(KeyEvent.VK_Q, Key.QUIT.getKeyObserver());
    }
}
