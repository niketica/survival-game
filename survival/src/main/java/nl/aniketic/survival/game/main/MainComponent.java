package nl.aniketic.survival.game.main;

import nl.aniketic.survival.game.gamestate.SurvivalGameStateManager;

public class MainComponent {

    public static void main(String[] args) {
        new SurvivalGameStateManager().startGame();
    }
}
