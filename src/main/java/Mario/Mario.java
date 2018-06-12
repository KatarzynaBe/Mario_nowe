package Mario;
import Enemies.Mushroom;
import Enemies.Turtle;
import Wall_etc.Flower;

import Windows.Main;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import Wall_etc.Wall;
import Wall_etc.Coins;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Mario {

    public static final double MOVE_SHIFT = 10;// przesuniecie
    double xPosition;
    double yPosition;
    static final int jumpHeight=5;//wysokosc skoku
    boolean jumping;
    public boolean falling;
    private Image image;
    private Image imageFlower;
    private Image imageBigMario;
    private Image imageSmallMario;
    int counter;
    public double minY;
    boolean shooting;
    int lives;
    int bigSmall;
    Media media = new Media(Main.class.getResource("/laugh.mp3").toString());

    public Mario() {


        yPosition = 360;
        image=new Image("/mario.png");
        imageSmallMario=new Image("/mario.png");
        imageFlower=new Image("/marioFlower.png");
        imageBigMario=new Image("/mario.png",100,150,false,false );
        this.jumping = false;
        this.falling = false;
        //this.jumpHeight = 5;
        counter=0;
        minY=360;
        lives=3;
        bigSmall=0;

    }

    public double getX() {
        return xPosition;
    }
    public double getY() {
        return yPosition;
    }
    public void setX(double x) {
        this.xPosition=x;
    }
    public void setY(double y) {
        this.yPosition = y;

    }
    public Image getImage()
    {

        return image;

    }
    public Image getImageFlower()
    {
        return imageFlower;
    }
    public int getLives()
    {
        return this.lives;
    }
    public void setLives(int a)
    {
        this.lives=a;
    }
    public int getBigSmall()
    {
        return bigSmall;
    }
    public void setBigSmall(int big)
    {
        this.bigSmall=big;
    }
    public void handlePlayerKey(KeyCode code) {

        if(code==KeyCode.LEFT) {
            moveLeft();

        }

        if(code==KeyCode.RIGHT){
            moveRight();

        }

        if(code==KeyCode.UP){
            moveUp();

        }

        if(code==KeyCode.DOWN){
            moveDown();
        }

    }


    private void moveLeft () {
        if (this.getX() > 0) {
            this.setX(this.getX() - MOVE_SHIFT);


        }
    }

    private void moveRight () {


        this.setX(this.getX() + MOVE_SHIFT);


    }

    private void moveUp () {
        if(jumping==false&&falling==false)
            jumping=true;
    }

    private void moveDown () {

        this.setY(this.getY() + MOVE_SHIFT);

    }
    public void Jumping() {
        for (int i = 0; i < 2; i++) {
            if(yPosition<minY&&jumping==false)
                falling=true;
            if (jumping==true) {

                yPosition -= jumpHeight;
                xPosition+=2;

                counter++;

                if (counter >= 60) {
                    jumping = false;
                    falling = true;
                }
            } else if (falling==true) {

                counter = 0;
                yPosition += jumpHeight;
                if (yPosition >= minY) {
                    falling = false;
                }

            }
        }
    }

    public void marioMoveBack()
    {
        xPosition-=4;
    }

    public void wallCollision(Wall wall) {

        if (xPosition + image.getWidth() >= wall.getX()
                && xPosition + image.getWidth() <= wall.getX() + wall.getImage().getWidth()
                && yPosition + image.getHeight() >= wall.getY()
                && yPosition + image.getHeight() <= wall.getY() + wall.getImage().getHeight()) {
            minY = (int) (wall.getY() - image.getHeight());
            if (yPosition > minY)
                yPosition = minY;
        }

        else if (xPosition >= wall.getX() && xPosition <= wall.getX() + wall.getImage().getWidth()
                && yPosition + image.getHeight() >= wall.getY()
                && yPosition + image.getHeight() <= wall.getY() + wall.getImage().getHeight()) {
            minY = (int) (wall.getY() - image.getHeight());
            if (yPosition > minY)
                yPosition = minY;
        }

        else if (xPosition + image.getWidth() / 2 >= wall.getX()
                && xPosition + image.getWidth() / 2 <= wall.getX() + wall.getImage().getWidth()
                && yPosition + image.getHeight() >= wall.getY()
                && yPosition + image.getHeight() <= wall.getY() + wall.getImage().getHeight()) {
            minY = (int) (wall.getY() - image.getHeight());
            if (yPosition > minY) // to avoid stacking in box
                yPosition = minY;
        }


        else if (xPosition >= wall.getX() && xPosition <= wall.getX() + wall.getImage().getWidth()
                && yPosition + image.getHeight() / 2 >= wall.getY()
                && yPosition + image.getHeight() / 2 <= wall.getY() + wall.getImage().getHeight()) {
            xPosition += MOVE_SHIFT;
        }

        else if (xPosition + image.getWidth() >= wall.getX()
                && xPosition + image.getWidth() <= wall.getX() + wall.getImage().getWidth()
                && yPosition + image.getHeight() / 2 >= wall.getY()
                && yPosition + image.getHeight() / 2 <= wall.getY() + wall.getImage().getHeight()) {
            xPosition -= MOVE_SHIFT;
        }

        else if (xPosition + image.getWidth() >= wall.getX()
                && xPosition + image.getWidth() <= wall.getX() + wall.getImage().getWidth() && yPosition >= wall.getY()
                && yPosition <= wall.getY() + wall.getImage().getHeight()) {
            if (jumping) {
                jumping = false;
                falling = true;
            }

            else if (xPosition <= wall.getX()) {
                xPosition -= MOVE_SHIFT;
            } else
                xPosition += MOVE_SHIFT;
        }

        else if (xPosition >= wall.getX() && xPosition <= wall.getX() + wall.getImage().getWidth() && yPosition >= wall.getY()
                && yPosition <= wall.getY() + wall.getImage().getHeight()) {
            if (jumping) {
                jumping = false;
                falling = true;


            }

            else if (xPosition <= wall.getX()) {
                xPosition -= MOVE_SHIFT;
            } else
                xPosition += MOVE_SHIFT;
        }

        else if (xPosition + image.getWidth() / 2 >= wall.getX()
                && xPosition + image.getWidth() / 2 <= wall.getX() + wall.getImage().getWidth() && yPosition >= wall.getY()
                && yPosition <= wall.getY() + wall.getImage().getHeight()) {
            if (jumping) {
                jumping = false;
                falling = true;


            }

            else if (xPosition <= wall.getX()) {
                xPosition -= MOVE_SHIFT;
            } else
                xPosition += MOVE_SHIFT;
        }

        if (Math.abs(yPosition - minY) <= 5 && !falling && !jumping && minY != (int) (460 - image.getHeight())) {
            minY = (int) (460 - image.getHeight());
            falling = true;
        }
    }
    public int turtleCollision(Turtle turtle)
    {
        if (xPosition+image.getWidth() >= turtle.getX()  && xPosition+image.getWidth() <= turtle.getX()+turtle.getImage().getWidth()
                ||xPosition>=turtle.getX()&&xPosition<=turtle.getX()+turtle.getImage().getWidth()) {
            if(-(yPosition+image.getHeight())+turtle.getY()<=10&&-(yPosition+image.getHeight())+turtle.getY()>=0)
            {
                return 1;
            }
            if (yPosition <=turtle.getY()+turtle.getImage().getHeight()&&yPosition>=turtle.getY()
                    ||yPosition<=turtle.getY()&&yPosition+image.getHeight()>=turtle.getY()+turtle.getImage().getHeight()
                    ||yPosition+image.getHeight()>=turtle.getY()&&yPosition+image.getHeight()<=turtle.getY()+turtle.getImage().getHeight())
            {
                if(bigSmall==0) {
                    lives--;
                }
                bigSmall=0;
                image=imageSmallMario;
                return 2;
            }
        }
        return 0;
    }
    public boolean coinCollision(Coins coin)
    {
        if (xPosition+image.getWidth() >= coin.getX()  && xPosition+image.getWidth() <= coin.getX()+coin.getImage().getWidth()
                ||xPosition>=coin.getX()&&xPosition<=coin.getX()+coin.getImage().getWidth()) {
            if (yPosition <=coin.getY()+coin.getImage().getHeight()&&yPosition>=coin.getY()
                    ||yPosition<=coin.getY()&&yPosition+image.getHeight()>=coin.getY()+coin.getImage().getHeight()
                    ||yPosition+image.getHeight()>=coin.getY()&&yPosition+image.getHeight()<=coin.getY()+coin.getImage().getHeight())
            {
                MediaPlayer mp = new MediaPlayer(media);
                mp.setCycleCount(0);
                mp.play();
                return true;
            }
        }
        return false;
    }
    public boolean flowerCollision(Flower flower)
    {
        if (xPosition+image.getWidth() >= flower.getX()  && xPosition+image.getWidth() <= flower.getX()+flower.getImage().getWidth()
                ||xPosition>=flower.getX()&&xPosition<=flower.getX()+flower.getImage().getWidth()) {
            if (yPosition <=flower.getY()+flower.getImage().getHeight()&&yPosition>=flower.getY()
                    ||yPosition<=flower.getY()&&yPosition+image.getHeight()>=flower.getY()+flower.getImage().getHeight()
                    ||yPosition+image.getHeight()>=flower.getY()&&yPosition+image.getHeight()<=flower.getY()+flower.getImage().getHeight())
            {

                return true;
            }
        }
        return false;
    }
    public boolean mushroomCollision(Mushroom mushroom)
    {
        if (xPosition+image.getWidth() >= mushroom.getX()  && xPosition+image.getWidth() <= mushroom.getX()+mushroom.getImage().getWidth()
                ||xPosition>=mushroom.getX()&&xPosition<=mushroom.getX()+mushroom.getImage().getWidth()) {
            if (yPosition <=mushroom.getY()+mushroom.getImage().getHeight()&&yPosition>=mushroom.getY()
                    ||yPosition<=mushroom.getY()&&yPosition+image.getHeight()>=mushroom.getY()+mushroom.getImage().getHeight()
                    ||yPosition+image.getHeight()>=mushroom.getY()&&yPosition+image.getHeight()<=mushroom.getY()+mushroom.getImage().getHeight())
            {
                bigSmall++;
                if(bigSmall>0)
                {

                    image=imageBigMario;
                }

                return true;
            }
        }
        return false;
    }



}
