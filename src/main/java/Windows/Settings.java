package Windows;
import Windows.SceneInterface;
import Windows.Scenes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Settings implements SceneInterface {
    private Scenes scenes;
    private Scene settingScene;
    private Group root;
    String string;

    public Settings(Scenes sceneManager) {
        this.scenes = sceneManager;

    }


    public Scene init(int width, int height) {
        root = new Group();
        settingScene = new Scene(root, width, height, Color.MEDIUMPURPLE);


        addSoundsOff();
        addMenuButton();

        return settingScene;
    }



    private void addSoundsOff() {
        if(scenes.sound==true)
            string="Sound off";
        else
            string="sound on";
        Button soundOff = new Button(string);
        soundOff.setPrefSize(200, 100);
        soundOff.setLayoutX(300);
        soundOff.setLayoutY(200);
        soundOff.setStyle("-fx-font: 22 arial;  -fx-base: rgb(90, 60, 90)");
        soundOff.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event)
            {

                if(scenes.sound==true) {
                    scenes.sound = false;
                    soundOff.setText("Sound on");
                }
                else {
                    scenes.sound = true;
                    soundOff.setText("Sound off");
                }

            }
        });

        root.getChildren().add(soundOff);
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