package snakegame.features.apples;

import snakegame.GamePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleManager {

    private Random random;
    private List<Apple> apples;

    public AppleManager() {
        startGame();
    }

    private void startGame() {
        apples = new ArrayList<>();
        random = new Random();
    }

    public List<Apple> getApples() {
        return apples;
    }

    public void spawnRndApple() {
        apples.add(
            new Apple(
                random.nextInt(GamePanel.GAME_WIDTH),
                random.nextInt(GamePanel.GAME_HEIGHT)
            )
        );
    }

    public void spawnPosApple(int x, int y) {
        apples.add(
            new Apple(
                x, y
            )
        );
    }

    public void removeRefApple(Apple a) {
        getApples().remove(a);
    }

    public void removePosApple(int x, int y) {

    }
}
