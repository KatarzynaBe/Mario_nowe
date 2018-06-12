package Windows;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Katarzyna Brzezińska
 * @version 1.0 06.2018
 */

public class Main extends Application
{

    public static final String TITLE = "Mario";
    public static final int SIZEx = 800;
    public static final int SIZEy = 550;
    /**
     * Constructor for Scenes class
     * @param primaryStage the Stage used by Scenes
     */
    public void start (Stage primaryStage)
    {
        primaryStage.setTitle(TITLE);
        Scenes scenes = new Scenes(primaryStage);
        scenes.goToMenuScene(scenes);
    }

    public static void Main(String[] args)
    {
        launch(args);
    }

}