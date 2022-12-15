package nl.aniketic.survival.game.gamestate;

public interface EntityController<T> {

    void loadEntity(int x, int y);

    void update();

    T getEntity();
}
