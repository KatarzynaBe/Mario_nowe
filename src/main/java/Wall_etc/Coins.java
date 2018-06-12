package Wall_etc;

import javafx.scene.image.Image;

public class Coins {
    double xPosition;
    double yPosition;
    int counter;
    private Image image=new Image("/coin.png",20,20,false,false );;

    public Coins(int x, int y)
    {
        this.xPosition=x;
        this.yPosition=y;


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
    public void coinMoveBack()
    {
        xPosition-=4;
    }
}
