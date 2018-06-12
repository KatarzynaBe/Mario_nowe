package Wall_etc;

import javafx.scene.image.Image;

public class Castle {
    double xPosition;
    double yPosition;

    private Image image=new Image("/castle.png",500,500,false,false );

    public Castle(int x, int y)
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
    public void castleMoveBack()
    {
        xPosition-=4;
    }
}



