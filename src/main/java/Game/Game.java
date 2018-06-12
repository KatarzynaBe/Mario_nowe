package Game;

import Enemies.Turtle;
import Wall_etc.*;
import Windows.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import Enemies.Mushroom;
import Windows.Scenes;
import Mario.Mario;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;


public class Game {
    private Scene gameScene;
    private Scenes scenes;
    private Group root;
    private Mario mario;
    boolean anotherMario;
    List<Turtle> turtles = new ArrayList();
    //Vector <Coins> coins=new Vector<>();
   // Turtle[] turtles=new Turtle[4];
    Wall[] wall = new Wall[300];
    Castle castle;
    int coin;
    int flowerCounter;
    public List<Coins> coins = new ArrayList<>();
    private List<Flower> flowers = new ArrayList<>();
    private List<Mushroom> mushrooms = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    Text t;
    Text livesText;
    Text timeText;
    Media media = new Media(Main.class.getResource("/mario.mp3").toString());

    MediaPlayer mp = new MediaPlayer(media);

    private long stepCounter = 0L;
    private double timer = 0.0;
    GraphicsContext gc;
    boolean paused;
    boolean bull;


    public Game(Scenes scenes) {
        this.scenes = scenes;
        coin = 0;
        anotherMario = false;
        paused = false;
    }

    private void quitToMenu() {
        scenes.goToMenuScene(scenes);
    }

    private void addBackground() {

        Image background = new Image("/background.png");
        gc.drawImage(background, 0, 0);


    }

    public Scene init(int width, int height) {
        root = new Group();
        gameScene = new Scene(root, width, height);
        Canvas canvas = new Canvas(800, 1000);
        root.getChildren().add(canvas);
        addMario();

        t = new Text(10, 30, "coins " + coin);
        livesText = new Text(10, 10, "lives " + mario.getLives());
        timeText = new Text(10, 50, "time " + (int)timer);
        root.getChildren().add(t);
        root.getChildren().add(livesText);
        root.getChildren().add(timeText);

        gc = canvas.getGraphicsContext2D();
        addBackground();
        addWalls();
       addTurtles();
       addCoins();
        addCastle();
        addFlowers();
       addMushrooms();


        gameScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));

        return gameScene;
    }

    private void handleKeyPressed(KeyCode code) {

        switch (code) {
            case Q:
                quitToMenu();
                mp.stop();
                break;
            case P:
                if(paused==false)
                    paused = true;
                else
                    paused=false;
                mp.stop();
                break;
            case SHIFT:
                if(bull==true) {
                     addBullets();
                    break;
                }
            default:

                mario.handlePlayerKey(code);
                break;
        }

    }

    public void step(double elapsedTime) {
        if(paused==false) {
            if (timer > 120) {
                scenes.goToGameOverScene(scenes);
                mp.stop();
            }
            checkTimeUp(elapsedTime);
            if (scenes.sound == true) {
                mp.setCycleCount(MediaPlayer.INDEFINITE);
                mp.play();
            }

        for (int i = 0; i < turtles.size(); i++)
            turtles.get(i).move();
        //for (int i = 0; i <turtles.length; i++)
        //    turtles[i].move();
        addBackground();
        drawMario();
        drawWalls();
        drawTurtles();
        drawCoins();
        checkIfIsAlive();
        Won();
        drawCastle();
        drawFlowers();
        addLive();
        drawMushrooms();
        drawBullets();



        for (int i = 0; i < coins.size(); i++) {
            if (mario.coinCollision(coins.get(i))) {
                coins.remove(i);
                coin++;
            }
        }

        for (int i = 0; i < mushrooms.size(); i++) {
            if (mushrooms.get(i).getDead() == false)
                mushrooms.get(i).move();
            for(int j=0;j<bullets.size();j++) {
                if (bullets.get(j).mushroomCollision(mushrooms.get(i)) == true) {
                    mushrooms.remove(i);
                }
            }
        }

        for (int i = 0; i < 250; i++) {
            mario.wallCollision(wall[i]);
            for (int j = 0; j < mushrooms.size(); j++) {
                if (mushrooms.get(j).wallCollision(wall[i])) {
                    boolean right = true;
                    mushrooms.get(j).slide(right);
                }

            }
        }
        for (int i = 0; i < turtles.size(); i++) {
            //for (int i = 0; i < turtles.length; i++){
            if (mario.turtleCollision(turtles.get(i)) == 2) {
               // if (mario.turtleCollision(turtles[i]) == 2) {
                mario.setX(mario.getX() - 100);
            }
            if (mario.turtleCollision(turtles.get(i)) == 1) {
                //if (mario.turtleCollision(turtles[i]) == 1) {
                turtles.remove(i);
                    //delete turtles[i];
            }
            for(int j=0;j<bullets.size();j++)
            {
               if(bullets.get(j).turtleCollision(turtles.get(i))==true)
               {
                   turtles.remove(i);
               }
            }

        }

        mario.Jumping();
        if (mario.getX() > 400) {
            mario.marioMoveBack();

            for (int j = 0; j < 250; j++) {

                wall[j].wallMoveBack();
            }
            for(int j=0;j<turtles.size();j++) {

                turtles.get(j).turtleMoveBack();
            }
            for(int j=0;j<coins.size();j++) {

                coins.get(j).coinMoveBack();
            }

            castle.castleMoveBack();
            for(int j=0;j<mushrooms.size();j++) {

                mushrooms.get(j).mushroomMoveBack();
            }
            for(int j=0;j<flowers.size();j++) {

                flowers.get(j).flowerMoveBack();
            }

        }

        for (int i = 0; i < flowers.size(); i++) {
            if (mario.flowerCollision(flowers.get(i)) == true) {
                anotherMario = true;
                flowers.remove(flowers.get(i));
                bull=true;
                flowerCounter=0;
            }
        }
       if(flowerCounter++>500) {
           anotherMario = false;
           bull = false;
       }
            for (int i = 0; i < mushrooms.size(); i++) {
                if (mario.mushroomCollision(mushrooms.get(i)) == true) {
                    mushrooms.remove(mushrooms.get(i));
                }
            }
            t.setText("coins " + coin);
            livesText.setText("lives " + mario.getLives());
            timeText.setText("time "+(int)timer);
            stepCounter++;
        }

    }
    private void checkTimeUp(double elapsedTime) {
        timer += elapsedTime;

    }

    private void addWalls()
    {

        for(int i=0;i<200;i++) {
            wall[i]= new Wall(i*30,460);
        }

        for(int i=200;i<210;i++) {
            wall[i]= new Wall((i-200)*30+500,290);
        }
        for(int i=210;i<220;i++) {
            wall[i]= new Wall(900+(i-210)*30,300);
        }
        for(int i=220;i<225;i++) {
            wall[i]= new Wall(1500+((i-220)*30),320);
        }
        for(int i=225;i<235;i++) {
            wall[i]= new Wall(1900+((i-225)*30),320);
        }
        for(int i=235;i<245;i++) {
            wall[i]= new Wall(2300+(i-235)*30,320);
        }
        for(int i=245;i<250;i++) {
            wall[i]= new Wall(2500+((i-245)*30),320);
        }
    }
    private void drawWalls()
    {
        for(int i=0;i<250;i++) {
            gc.drawImage(wall[i].getImage(), wall[i].getX(), wall[i].getY());
        }
    }

    private void addTurtles()
    {

       addturtle(1900,270);
       addturtle(500,240);
       addturtle(200,410);
       addturtle(800,410);
    }
    private void addturtle(int x, int y)
    {
        Turtle turtle=new Turtle(x,y);
        turtles.add(turtle);
        //turtles[i]=new Turtle (x,y);
    }
    private void drawTurtles()
    {
        turtles.forEach(turtle->gc.drawImage(turtle.getImage(), turtle.getX(), turtle.getY()));
      /*  for(int i=0;i<4;i++) {
            gc.drawImage(turtles[i].getImage(), turtles[i].getX(), turtles[i].getY());
        }*/

    }

    void addCoins()
    {
       addcoin(800,440);
       addcoin(200,440);

    }
    private void addcoin(int x, int y)
    {
        Coins coin1=new Coins(x,y);
        coins.add(coin1);
    }
    private void drawCoins()
    {
        coins.forEach(coin1->gc.drawImage(coin1.getImage(), coin1.getX(), coin1.getY()));
    }
    void checkIfIsAlive()
    {
        if(mario.getLives()<=0) {
            scenes.goToGameOverScene(scenes);
            mp.stop();
        }
    }
    void Won() {
        if (mario.getX() >= castle.getX()+100) {
            scenes.goToGameWonScene(scenes);
            mp.stop();
        }
    }
    void addCastle()
    {
      castle=new Castle(3000,0);
    }
   void drawCastle()
    {
        gc.drawImage(castle.getImage(), castle.getX(), castle.getY());
    }
    private void addLive() {
        if (coin == 10) {
            mario.setLives((mario.getLives()) + 1);
            coin = 0;

        }
    }

    void addFlowers()
    {
        addflower(1300,420);

    }
    private void addflower(int x, int y)
    {
        Flower flower=new Flower(x,y);
        flowers.add(flower);
    }
    private void drawFlowers()
    {
        flowers.forEach(flower->gc.drawImage(flower.getImage(), flower.getX(), flower.getY()));
    }
    void addMario()
    {
        mario=new Mario();
    }
    void drawMario()
    {
        if(anotherMario==false)
        gc.drawImage(mario.getImage(), mario.getX(), mario.getY());
        else
            gc.drawImage(mario.getImageFlower(),mario.getX(),mario.getY());
    }
    void addMushrooms()
    {
        addmushroom(120,430);
    }
    private void addmushroom(int x, int y)
    {
        Mushroom mushroom=new Mushroom(x,y);
        mushrooms.add(mushroom);
    }
    private void drawMushrooms()
    {
        mushrooms.forEach(mushroom->gc.drawImage(mushroom.getImage(), mushroom.getX(), mushroom.getY()));
    }
    void addBullets()
    {
        addbullet(mario.getX(),(mario.getY()));
    }
    private void addbullet(double x, double y)
    {
        Bullet bullet=new Bullet(x,y+30);
        bullets.add(bullet);
    }
    private void drawBullets()
    {
        bullets.forEach(bullet->gc.drawImage(bullet.getImage(), bullet.getX(), bullet.getY()));
        bullets.forEach(bullet->bullet.move());
    }

}


