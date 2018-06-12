package Enemies;

import Wall_etc.Wall;
import javafx.scene.image.Image;

public class Mushroom { // DRY
    double xPosition;
    double yPosition;
    int counter;
    boolean dead;
    private Image image = new Image("/mushroom.png", 30, 20, false, false);
    ;

    public Mushroom(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
        counter = 0;
        dead=false;

    }

    public double getX() {
        return xPosition;
    }

    public double getY() {
        return yPosition;
    }

    public void setX(double x) {
        this.xPosition = x;
    }

    public void setY(double y) {
        this.yPosition = y;

    }

    public Image getImage() {
        return image;
    }
    public boolean getDead()
    {
        return dead;
    }
    public void move()
    {
        if (counter<60)
        {
            xPosition+=3;
            counter++;
        }
        if(counter>=60&&counter<120)
        {
            xPosition-=3;
            counter++;
        }
        if (counter==120)
            counter=0;

    }

    public void slide(boolean rightLeft) {
        if(rightLeft==true)
            xPosition += 5;
        else
            xPosition-=5;
    }

    public boolean wallCollision(Wall wall)
    {
        if (xPosition <= wall.getX() && xPosition+image.getWidth() >= wall.getX()+wall.getImage().getWidth()) {

            if (yPosition <=wall.getY()-wall.getImage().getHeight())
            {
                return true;
            }
        }
        return false;
    }
    public void mushroomMoveBack()
    {
        xPosition-=4;
    }
}

