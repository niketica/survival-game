package nl.aniketic.survival.controls;

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
        for (EditorTile editorTile : userInterface.getEditorTiles()) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            int size = editorTile.getSize();
            int x1 = editorTile.getX();
            int x2 = editorTile.getX() + size;
            int y1 = editorTile.getY();
            int y2 = editorTile.getY() + size;

            boolean selected = mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
            editorTile.setSelected(selected);
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
