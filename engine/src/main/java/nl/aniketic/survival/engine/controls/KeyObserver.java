package nl.aniketic.survival.engine.controls;

public class KeyObserver {

    private String name;
    private boolean pressed;

    public KeyObserver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
