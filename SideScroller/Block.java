import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Block {

    private int x;
    private int y;
    private BufferedImage image;

    public Block(int x, int y, BufferedImage image) {
        this.x      =   x;
        this.y      =   y;
        this.image  =   image;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public BufferedImage getImage() { return image; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setImage(BufferedImage image) { this.image = image; }
}
