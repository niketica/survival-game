package nl.aniketic.survival.mapeditor;

public class Button {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String text;

    private boolean clicked;

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
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
        return text;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;

        if (clicked) {
            System.out.println("Button clicked!");
        }
    }
}
