package nl.aniketic.survival.controls;

import nl.aniketic.survival.mapeditor.Button;
import nl.aniketic.survival.mapeditor.EditorTile;
import nl.aniketic.survival.mapeditor.UserInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorMouseListener implements MouseListener {

    private final UserInterface userInterface;

    public EditorMouseListener(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        checkEditorTileClicked(e);
        checkButtonClicked(e);
    }

    private void checkEditorTileClicked(MouseEvent e) {
        for (EditorTile editorTile : userInterface.getEditorTiles()) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            int size = editorTile.getSize();
            int x1 = editorTile.getX();
            int x2 = editorTile.getX() + size;
            int y1 = editorTile.getY();
            int y2 = editorTile.getY() + size;

            boolean clicked = mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
            if (clicked && !editorTile.isSelected()) {
                userInterface.getEditorTiles().forEach(v -> v.setSelected(false));
                editorTile.setSelected(true);
                break;
            }
        }
    }

    private void checkButtonClicked(MouseEvent e) {
        for (Button button : userInterface.getButtons()) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            int width = button.getWidth();
            int height = button.getHeight();
            int x1 = button.getX();
            int x2 = button.getX() + width;
            int y1 = button.getY();
            int y2 = button.getY() + height;

            boolean clicked = mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
            button.setClicked(clicked);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
