import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Image;

public abstract class Character {

    protected Image currentImage;

    private int     x;
    private int     y;

    public Character(int x, int y) {
        this.x  =   x;
        this.y  =   y;
    }

    abstract public BufferedImage getImage(int i);
    abstract public void          setImage(int i);

    public Image getCurrentImage() { return currentImage; }
    public int getX() { return x; }
    public int getY() { return y; }

    public void setCurrentImage(Image currentImage) { this.currentImage = currentImage; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

}
