import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Character {

    protected Image currentImage;

    private     int x;
    private     int y;
    protected   int width;
    protected   int height;

    public Character(int x, int y) {
        this.x  =   x;
        this.y  =   y;
    }

    public Image getCurrentImage() { return currentImage; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setCurrentImage(Image currentImage) { this.currentImage = currentImage; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

}
