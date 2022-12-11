package nl.aniketic.survival.engine.display;

import java.awt.Graphics2D;

public interface PanelComponent {

    default void activate() {
        GamePanel.addPanelComponent(this);
    }

    default void deactivate() {
        GamePanel.removePanelComponent(this);
    }

    void paintComponent(Graphics2D g2);
}
