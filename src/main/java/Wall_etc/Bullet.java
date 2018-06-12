package Wall_etc;

import Enemies.Mushroom;
import Enemies.Turtle;
import javafx.scene.image.Image;

public class Bullet {
    double xPosition;
    double yPosition;
    private Image image=new Image("/bullet.png",50,50,false,false );
    public Bullet(double x, double y)
    {
        xPosition=x;
        yPosition=y;
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
        xPosition+=5;
    }

    public boolean turtleCollision(Turtle turtle)
    {
        if (xPosition+image.getWidth() >= turtle.getX()  && xPosition+image.getWidth() <= turtle.getX()+turtle.getImage().getWidth()
                ||xPosition>=turtle.getX()&&xPosition<=turtle.getX()+turtle.getImage().getWidth()) {
            if (yPosition <=turtle.getY()+turtle.getImage().getHeight()&&yPosition>=turtle.getY()
                    ||yPosition<=turtle.getY()&&yPosition+image.getHeight()>=turtle.getY()+turtle.getImage().getHeight()
                    ||yPosition+image.getHeight()>=turtle.getY()&&yPosition+image.getHeight()<=turtle.getY()+turtle.getImage().getHeight())
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

                return true;
            }
        }
        return false;
    }

}
