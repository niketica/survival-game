package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.Crowbar;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.ArrayList;
import java.util.List;

public class CrowbarController implements EntityController<Crowbar> {

    private List<Crowbar> crowbars;
    private LevelManager levelManager;

    public CrowbarController(LevelManager levelManager) {
        this.levelManager = levelManager;
        crowbars = new ArrayList<>();
    }

    @Override
    public void loadEntity(int x, int y) {
        Node node = levelManager.getNode(x, y);
        Crowbar crowbar = new Crowbar();
        crowbar.setWorldPosition(node);
        crowbar.activate();
        crowbars.add(crowbar);
    }

    @Override
    public void update() {

    }

    @Override
    public Crowbar getEntity() {
        return null;
    }

    @Override
    public void removeEntity(Crowbar crowbar) {
        crowbar.deactivate();
        crowbars.remove(crowbar);
    }

    @Override
    public List<Crowbar> getEntities() {
        return crowbars;
    }
}
