package Wall_etc;

import javafx.scene.image.Image;

public class Wall {
    private int x;
    private int y;
    private Image image = new Image("/wall.png",30,30,false,false);

    public Wall(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void wallMoveBack()
    {
        x-=4;
    }
}
