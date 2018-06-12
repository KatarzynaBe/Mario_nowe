package Windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameWon implements SceneInterface {
    private Scenes scenes;
    private Scene gameWonScene;
    private Group root;


    public GameWon(Scenes sceneManager) {
        this.scenes = sceneManager;
    }



    public Scene init(int width, int height) {
        root = new Group();
        gameWonScene = new Scene(root, width, height, Color.MEDIUMPURPLE);
        addGameWonText();
        addMenuButton();
        return gameWonScene;
    }

    private void addGameWonText() {
        Text gameWonText = new Text();
        gameWonText.setText("Game Won");
        gameWonText.setX(150);
        gameWonText.setY(200);
        gameWonText.setFont(Font.font("Arial", 100));
        gameWonText.setFill(Color.BLACK);
        root.getChildren().add(gameWonText);
    }

    private void addMenuButton() {

        Button menuButton = new Button("Play again");
        menuButton.setPrefSize(200, 100);
        menuButton.setLayoutX(300);
        menuButton.setLayoutY(350);
        menuButton.setStyle("-fx-font: 22 arial;  -fx-base: rgb(90, 60, 90)");
        menuButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                scenes.goToMenuScene(scenes);
            }
        });

        root.getChildren().add(menuButton);
    }
}
