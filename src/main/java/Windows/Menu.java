package Windows;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Menu implements SceneInterface
{
    private Scenes scenes;
    private Scene menuScene;
    private Group root;

    public Menu(Scenes scenes)
    {
        this.scenes = scenes;
    }

    public Scene init(int width, int height)
    {
        root = new Group();
        Image image = new Image("/xxx.png",true);
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        root.getChildren().add(iv1);
        menuScene = new Scene(root, width, height);



        addSettingsButton();
        addExitButton();
        addStartButton();

        return menuScene;
    }


    private void addSettingsButton()
    {
        Button SettingsButton = new Button ("Settings");
        SettingsButton.setPrefSize(150, 50);
        SettingsButton.setLayoutX(400);
        SettingsButton.setLayoutY(450);

        SettingsButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                scenes.goToSettingsScene(scenes);
            }
        });

        root.getChildren().add(SettingsButton);
    }
    private void addStartButton()
    {
        Button startButton = new Button("Play Game");
        startButton.setPrefSize(150, 50);
        startButton.setLayoutX(400);
        startButton.setLayoutY(350);
        startButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                scenes.goToGameScene(scenes);
            }
        });

        root.getChildren().add(startButton);
    }

    private void addExitButton()
    {
        Button ExitButton = new Button ("Exit");
        ExitButton.setPrefSize(150, 50);
        ExitButton.setLayoutX(400);
        ExitButton.setLayoutY(550);

        ExitButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

                System.exit(0);
            }
        });

        root.getChildren().add(ExitButton);
    }
}