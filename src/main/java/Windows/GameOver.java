package Windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameOver implements SceneInterface {
    private Scenes scenes;
    private Scene gameOverScene;
    private Group root;


    public GameOver(Scenes sceneManager) {
        this.scenes = sceneManager;
    }



    public Scene init(int width, int height) {
        root = new Group();
        gameOverScene = new Scene(root, width, height, Color.BLACK);
        addGameOverText();
        addMenuButton();
        return gameOverScene;
    }

    private void addGameOverText() {
        Text gameOverText=new Text();
        gameOverText.setText("Game Over");
        gameOverText.setX(150);
        gameOverText.setY(200);
        gameOverText.setFont(Font.font("Arial", 100));
        gameOverText.setFill(Color.RED);
        root.getChildren().add(gameOverText);
    }

    private void addMenuButton() {

        Button menuButton = new Button("Play again");
        menuButton.setPrefSize(200, 100);
        menuButton.setLayoutX(300);
        menuButton.setLayoutY(350);
        menuButton.setStyle("-fx-font: 22 arial;  -fx-base: rgb(90, 60, 90)");
        menuButton.setOnAction(new EventHandler<ActionEvent>()
        {

            public void handle(ActionEvent event) {
                scenes.goToMenuScene(scenes);
            }
        });

        root.getChildren().add(menuButton);
    }
}