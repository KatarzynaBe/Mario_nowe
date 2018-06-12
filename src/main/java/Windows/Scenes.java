package Windows;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import Game.Game;
import Windows.Settings;

public class Scenes {
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static Stage stage;
    private static Timeline animation;
    public boolean sound;


    public Scenes(Stage primaryStage) {
        this.stage = primaryStage;
        this.animation = new Timeline();
        stage.show();
        sound=true;
    }


    public void goToMenuScene(Scenes scenes) {
        animation.stop();
        Menu menu = new Menu(scenes);
        Scene menuScene = menu.init(1000,640);
        stage.setScene(menuScene);
    }




    public void goToGameOverScene(Scenes scenes) {
        animation.stop();
        GameOver gameOver = new GameOver(scenes);
        Scene gameOverScene = gameOver.init(Main.SIZEx, Main.SIZEy);
        stage.setScene(gameOverScene);
    }


    public void goToGameWonScene(Scenes scenes) {
        animation.stop();
        GameWon gameWon = new GameWon(scenes);
        Scene gameWonScene = gameWon.init(Main.SIZEx, Main.SIZEy);
        stage.setScene(gameWonScene);
    }

    public void goToSettingsScene(Scenes scenes) {

        Settings settings = new Settings(scenes);
        Scene settingScene = settings.init(Main.SIZEx, Main.SIZEy);
        stage.setScene(settingScene);

    }

    public void goToGameScene(Scenes scenes)
    {
        Game game = new Game(scenes);
        Scene GameScene = game.init(Main.SIZEx, Main.SIZEy);
        stage.setScene(GameScene);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> game.step(SECOND_DELAY));
        setGameLoop(frame);

    }



    private void setGameLoop(KeyFrame frame) {
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
}