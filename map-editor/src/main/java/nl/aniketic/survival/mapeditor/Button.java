package nl.aniketic.survival.mapeditor;

public class Button {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final ButtonValue buttonValue;

    private boolean clicked;

    public Button(int x, int y, int width, int height, ButtonValue buttonValue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonValue = buttonValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return buttonValue.name();
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public ButtonValue getButtonValue() {
        return buttonValue;
    }
}
