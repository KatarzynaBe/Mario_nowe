package Enemies;

import javafx.scene.image.Image;

public class Turtle {
    double xPosition;
    double yPosition;
    int counter;
    private Image image=new Image("/dino2.png",70,50,false,false );

    public Turtle(int x, int y)
    {
        this.xPosition=x;
        this.yPosition=y;
        counter=0;

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
    public void move()
    {
        if (counter<90)
        {
            xPosition+=3;
            counter++;
        }
        if(counter>=90&&counter<180)
        {
            xPosition-=3;
            counter++;
        }
        if (counter==180)
            counter=0;

    }


    public void turtleMoveBack()
    {
        xPosition-=4;
    }
}
