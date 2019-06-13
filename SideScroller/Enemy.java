import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Enemy extends Character {

    Image   image;

    int     relativeX;
    String  direction; // left, right

    public Enemy(Image image, int x, int y) {
        super(x, y);
        this.image = image;
        relativeX = 0;
        direction = "left";
    }

    public Image getImage() { return image; }
    public int getRelativeX() { return relativeX; }
    public String getDirection() { return direction; }

    public void setImage(Image image) { this.image = image; }
    public void setRelativeX(int relativeX) { this.relativeX = relativeX; }
    public void setDirection(String direction) { this.direction = direction; }

}
